package com.gusrylmubarok.sinaukoding.hris.service;

import com.gusrylmubarok.sinaukoding.hris.HrisApplication;
import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.DivisionDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DivisionService extends BaseService<Division> {

    @Autowired
    private DivisionDAO dao;

    @Override
    protected BaseDAO<Division> getDAO() {
        return dao;
    }

    @Transactional
    public Division save(Division entity) {
        entity.setCreatedBy(HrisApplication.getCurrentUser().getUsername());

        return dao.save(entity);
    }

    @Transactional
    public Division update(Division entity) {
        entity.setUpdatedBy(HrisApplication.getCurrentUser().getUsername());

        return dao.save(entity);
    }

    @Transactional
    public Division delete(Division entity) {
        entity.setDeletedBy(HrisApplication.getCurrentUser().getUsername());

        return dao.save(entity);
    }
}
