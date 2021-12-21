package com.gusrylmubarok.sinaukoding.hris.controllers;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("isFullyAuthenticated")
public abstract class BaseController {
}
