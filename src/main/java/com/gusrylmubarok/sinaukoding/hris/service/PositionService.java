package com.gusrylmubarok.sinaukoding.hris.service;


import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.PositionDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PositionService extends BaseService<Position> {

    @Autowired
    private PositionDAO dao;

    @Override
    protected BaseDAO<Position> getDAO() {
        return dao;
    }

    @Transactional
    public Position update(Position entity) {
        if (entity.getId() != null) {
            Position reference = getDAO().findReference(entity.getId());

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

    public Position findByName(Position param) {
        return dao.findByName(param);
    }

}
