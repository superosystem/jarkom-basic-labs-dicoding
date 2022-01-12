package com.gusrylmubarok.sinaukoding.hris.service;

import com.gusrylmubarok.sinaukoding.hris.HrisApplication;
import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

public abstract class BaseService<T extends BaseEntity<T>> {

    protected abstract BaseDAO<T> getDAO();

    @Transactional(readOnly = true)
    public T findOne(T param){
        return getDAO().findOne(param);
    }

    @Transactional(readOnly = true)
    public Collection<T> find(T param, int offset, int limit){
        return getDAO().find(param, offset, limit);
    }

    @Transactional(readOnly = true)
    public Long count(T param){
        return getDAO().count(param);
    }

    @Transactional
    public T save(T entity){
        return getDAO().save(entity);
    }

    @Transactional
    public T update(T entity){
        if (entity.getId() != null){
            entity.getCreatedBy();
            return getDAO().update(entity);
        }
        return null;
    }

    @Transactional
    public T findById(Long id) {
        return getDAO().findReference(id);
    }

    @Transactional
    public boolean delete(Long id){
        return getDAO().delete(getDAO().findReference(id)) != null;
    }

    @Transactional
    public void updateDeleteStatus(Long id) {
        T reference = getDAO().findReference(id);
        if (reference != null) {
            reference.setDeletedTime(reference.getDeletedTime() != null
                    ? reference.getDeletedTime()
                    : new Date());

            reference.setDeletedBy(reference.getDeletedBy() != null
                    ? reference.getDeletedBy()
                    : HrisApplication.getCurrentUser().getId());
        }
    }

}
