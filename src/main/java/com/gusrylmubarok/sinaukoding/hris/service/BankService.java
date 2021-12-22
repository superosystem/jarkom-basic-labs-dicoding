package com.gusrylmubarok.sinaukoding.hris.service;

import com.gusrylmubarok.sinaukoding.hris.dao.BankDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService extends BaseService<Bank> {

    @Autowired
    private BankDAO dao;

    @Override
    protected BaseDAO<Bank> getDAO() {
        return dao;
    }


}
