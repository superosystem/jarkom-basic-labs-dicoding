package com.gusrylmubarok.sinaukoding.hris.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusrylmubarok.sinaukoding.hris.common.RestResult;
import com.gusrylmubarok.sinaukoding.hris.common.StatusCode;
import com.gusrylmubarok.sinaukoding.hris.entity.User;
import com.gusrylmubarok.sinaukoding.hris.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("users")
@PreAuthorize("permitAll()")
public class UserController extends BaseController {

    @Autowired
    private UserService service;

    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {
        User user = param != null ? new ObjectMapper().readValue(param, User.class) : null;

        long rows = service.count(user);

        return new RestResult(rows > 0 ? service.find(user, offset, limit) : new ArrayList<>(), rows);
    }

    @PutMapping
    public RestResult update(@RequestBody User user) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (user != null) {
            result.setData(service.update(user));
            result.setStatus(StatusCode.UPDATE_SUCCESS);
        }

        return result;
    }

    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id) {
        boolean deleted = false;
        User entity = service.findById(id);

        if (entity != null) {
            service.updateDeleteStatus(id);
            deleted = service.delete(id);
        }

        return new RestResult(deleted ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }

}
