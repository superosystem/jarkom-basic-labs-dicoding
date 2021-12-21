package com.gusrylmubarok.sinaukoding.hris.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusrylmubarok.sinaukoding.hris.common.RestResult;
import com.gusrylmubarok.sinaukoding.hris.common.StatusCode;
import com.gusrylmubarok.sinaukoding.hris.entity.Company;
import com.gusrylmubarok.sinaukoding.hris.service.CompanyService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/companies")
@PreAuthorize("permitAll()")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService service;

    @GetMapping
    private RestResult get(@RequestParam(value = "param", required = false) String param,
                           @RequestParam(value = "offset") int offset,
                           @RequestParam(value = "limit") int limit) throws JsonProcessingException {
        Company company = param != null ? new ObjectMapper().readValue(param, Company.class) : null;

        long rows = service.count(company);

        return new RestResult(rows > 0 ? service.find(company, offset, limit) : new ArrayList<>(), rows);
    }

    @PostMapping
    private RestResult save(@RequestBody Company param) {
        param = service.save(param);

        return new RestResult(param, param != null ? StatusCode.SAVE_SUCCESS : StatusCode.SAVE_FAILED);
    }

    @PutMapping
    private RestResult update(@RequestBody Company company) {
        company = service.update(company);

        return new RestResult(company, company != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
    }

    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id) {
        return new RestResult(service.delete(id) ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }


}
