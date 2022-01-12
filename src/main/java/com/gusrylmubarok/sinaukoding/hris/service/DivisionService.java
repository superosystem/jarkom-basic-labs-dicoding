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
    public Division update(Division entity) {
        if (entity.getId() != null) {
            Division reference = getDAO().findReference(entity.getId());

            reference.setName(entity.getName() != null
                    ? entity.getName()
                    : reference.getName());

            reference.setNote(entity.getNote() != null
                    ? entity.getNote()
                    : reference.getNote());

            entity = reference;

            return entity;
        }

        return null;
    }

    public Division findByName(Division param) {
        return dao.findByName(param);
    }
}
