package com.gusrylmubarok.sinaukoding.hris.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusrylmubarok.sinaukoding.hris.common.RestResult;
import com.gusrylmubarok.sinaukoding.hris.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/positions")
@PreAuthorize("permitAll()")
public class PositionService extends BaseController{

    @Autowired
    private PositionService service;

    @GetMapping
    public RestResult get(@RequestParam(value = "param") String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {
        Position postion = param != null ? new ObjectMapper().readValue(param, Position.class) : null;

    }
}
