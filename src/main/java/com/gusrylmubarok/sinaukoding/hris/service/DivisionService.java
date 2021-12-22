package com.gusrylmubarok.sinaukoding.hris.service;

import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.DivisionDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionService extends BaseService<Division> {

    @Autowired
    private DivisionDAO dao;

    @Override
    protected BaseDAO<Division> getDAO() {
        return dao;
    }
}
