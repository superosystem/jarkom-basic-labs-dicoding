package com.gusrylmubarok.sinaukoding.hris.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusrylmubarok.sinaukoding.hris.common.RestResult;
import com.gusrylmubarok.sinaukoding.hris.common.StatusCode;
import com.gusrylmubarok.sinaukoding.hris.entity.Position;
import com.gusrylmubarok.sinaukoding.hris.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("positions")
@PreAuthorize("permitAll()")
public class PositionController extends BaseController {

    @Autowired
    private PositionService service;

    @PreAuthorize("permitAll()")
    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {
        Position position = param != null ? new ObjectMapper().readValue(param, Position.class) : null;

        long rows = service.count(position);

        return new RestResult(rows > 0 ? service.find(position, offset, limit) : new ArrayList<>(), rows);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public RestResult save(@RequestBody Position param) {
        param = service.save(param);

        return new RestResult(param, param != null ? StatusCode.SAVE_SUCCESS : StatusCode.SAVE_FAILED);
    }

    @PreAuthorize("permitAll()")
    @PutMapping
    public RestResult update(@RequestBody Position position) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (position != null) {
            result.setData(service.update(position));
            result.setStatus(StatusCode.UPDATE_SUCCESS);
        }

        return result;
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id) {
        boolean deleted = false;
        Position entity = service.findById(id);

        if (entity != null) {
            service.updateDeleteStatus(id);
            deleted = service.delete(id);
        }

        return new RestResult(deleted ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
