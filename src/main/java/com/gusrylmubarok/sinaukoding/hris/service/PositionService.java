package com.gusrylmubarok.sinaukoding.hris.service;


import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.PositionDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService extends BaseService<Position> {

    @Autowired
    private PositionDAO dao;

    @Override
    protected BaseDAO<Position> getDAO() {
        return dao;
    }

}
