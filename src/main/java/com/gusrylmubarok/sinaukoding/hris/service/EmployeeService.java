package com.gusrylmubarok.sinaukoding.hris.service;

import com.gusrylmubarok.sinaukoding.hris.HrisApplication;
import com.gusrylmubarok.sinaukoding.hris.dao.BaseDAO;
import com.gusrylmubarok.sinaukoding.hris.dao.EmployeeDAO;
import com.gusrylmubarok.sinaukoding.hris.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class EmployeeService extends BaseService<Employee> {

    @Autowired
    private EmployeeDAO dao;

    @Override
    protected BaseDAO<Employee> getDAO() {
        return dao;
    }

    @Transactional
    public Employee save(Employee entity) {
        entity.setStartDate(new Date());
        entity.setUser(HrisApplication.getCurrentUser());

        return dao.save(entity);
    }

    @Transactional
    public Employee update(Employee entity) {
        if (entity.getId() != null) {
            Employee reference = getDAO().findReference(entity.getId());

            reference.setEndDate(entity.getEndDate() != null
                    ? entity.getEndDate()
                    : new Date());

            reference.setStatus(reference.getStatus().equals(Employee.StatusEmployee.ACTIVE)
            ? Employee.StatusEmployee.INACTIVE : reference.getStatus());

            entity.setStartDate(reference.getStartDate());
            entity.setEndDate(reference.getEndDate());
            entity.setStatus(reference.getStatus());

            return entity;
        }
        return null;
    }
}
