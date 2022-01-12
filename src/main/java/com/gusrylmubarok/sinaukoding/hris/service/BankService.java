package com.gusrylmubarok.sinaukoding.hris.service;

import com.gusrylmubarok.sinaukoding.hris.dao.BankDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService extends BaseService<Bank> {

    @Autowired
    private BankDAO dao;

    @Override
    protected BaseDAO<Bank> getDAO() {
        return dao;
    }

    @Transactional
    public Bank update(Bank entity) {
        if (entity.getId() != null) {
            Bank reference = getDAO().findReference(entity.getId());

            reference.setName(entity.getName() != null
                    ? entity.getName()
                    : reference.getName());

            reference.setCode(entity.getCode() != null
                    ? entity.getCode()
                    : reference.getCode());

            entity = reference;

            return entity;
        }

        return null;
    }

    public Bank findByName(Bank param) {
        return dao.findByName(param);
    }
}
