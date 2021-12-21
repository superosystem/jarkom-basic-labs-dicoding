package com.gusrylmubarok.sinaukoding.hris.controllers;

import com.gusrylmubarok.sinaukoding.hris.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@PreAuthorize("permitAll()")
public class UserController extends BaseController{

    @Autowired
    private UserService service;
}
