package com.gusrylmubarok.sinaukoding.hris.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusrylmubarok.sinaukoding.hris.common.RestResult;
import com.gusrylmubarok.sinaukoding.hris.common.StatusCode;
import com.gusrylmubarok.sinaukoding.hris.entity.Division;
import com.gusrylmubarok.sinaukoding.hris.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("divisions")
public class DivisionController extends BaseController {

    @Autowired
    private DivisionService service;

    @PreAuthorize("permitAll()")
    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {
        Division division = param != null ? new ObjectMapper().readValue(param, Division.class) : null;

        long rows = service.count(division);

        return new RestResult(rows > 0 ? service.find(division, offset, limit) : new ArrayList<>(), rows);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public RestResult save(@RequestBody Division param) {
        param = service.save(param);

        return new RestResult(param, param != null ? StatusCode.SAVE_SUCCESS : StatusCode.SAVE_FAILED);
    }

    @PreAuthorize("permitAll()")
    @PutMapping
    public RestResult update(@RequestBody Division division) {
        division = service.update(division);

        return new RestResult(division, division != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id) {
        return new RestResult(service.delete(id) ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
