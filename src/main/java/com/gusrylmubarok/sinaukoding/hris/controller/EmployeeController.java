package com.gusrylmubarok.sinaukoding.hris.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusrylmubarok.sinaukoding.hris.common.RestResult;
import com.gusrylmubarok.sinaukoding.hris.common.StatusCode;
import com.gusrylmubarok.sinaukoding.hris.entity.Employee;
import com.gusrylmubarok.sinaukoding.hris.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("employees")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService service;

    @PreAuthorize("permitAll()")
    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {
        Employee employee = param != null ? new ObjectMapper().readValue(param, Employee.class) : null;

        long rows = service.count(employee);

        return new RestResult(rows > 0 ? service.find(employee, offset, limit) : new ArrayList<>(), rows);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public RestResult save(@RequestBody Employee entity) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null) {
            result.setData(service.save(entity));
            result.setStatus(StatusCode.SAVE_SUCCESS);
        }

        return result;
    }

    @PreAuthorize("permitAll()")
    @PutMapping
    public RestResult update(@RequestBody Employee employee) {
        employee = service.update(employee);

        return new RestResult(employee, employee != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id) {
        return new RestResult(service.delete(id) ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }

}
