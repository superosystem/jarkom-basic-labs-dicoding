package com.gusrylmubarok.sinaukoding.hris.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gusrylmubarok.sinaukoding.hris.common.RestResult;
import com.gusrylmubarok.sinaukoding.hris.common.StatusCode;
import com.gusrylmubarok.sinaukoding.hris.entity.Attendance;
import com.gusrylmubarok.sinaukoding.hris.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("attendances")
public class AttendanceController extends BaseController {
    @Autowired
    private AttendanceService service;

    @PreAuthorize("permitAll()")
    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {
        Attendance attendance = param != null ? new ObjectMapper().readValue(param, Attendance.class) : null;

        long rows = service.count(attendance);

        return new RestResult(rows > 0 ? service.find(attendance, offset, limit) : new ArrayList<>(), rows);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public RestResult save(@RequestBody Attendance entity) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null) {
            result.setData(service.save(entity));
            result.setStatus(StatusCode.SAVE_SUCCESS);
        }

        return result;
    }

    @PreAuthorize("permitAll()")
    @PutMapping
    public RestResult update(@RequestBody Attendance attendance) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (attendance != null) {
            result.setData(service.update(attendance));
            result.setStatus(StatusCode.UPDATE_SUCCESS);
        }

        return result;
    }

    @PutMapping("/start-rest")
    public RestResult startRest(@RequestBody Attendance param) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (param != null) {
            result.setData(service.startRest(param));
            result.setStatus(StatusCode.UPDATE_SUCCESS);
        }

        return result;
    }

    @PutMapping("/end-rest")
    public RestResult endRest(@RequestBody Attendance param) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (param != null) {
            result.setData(service.endRest(param));
            result.setStatus(StatusCode.UPDATE_SUCCESS);
        }

        return result;
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id) {
        boolean deleted = false;
        Attendance entity = service.findById(id);

        if (entity != null) {
            service.updateDeleteStatus(id);
            deleted = service.delete(id);
        }

        return new RestResult(deleted ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }


}
