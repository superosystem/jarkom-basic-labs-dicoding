package com.gusrylmubarok.sinaukoding.hris.service;

import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.UserDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO dao;

    @Override
    protected BaseDAO<User> getDAO() {
        return dao;
    }
}
