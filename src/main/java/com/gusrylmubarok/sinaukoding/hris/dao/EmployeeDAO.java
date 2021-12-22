package com.gusrylmubarok.sinaukoding.hris.dao;

import com.gusrylmubarok.sinaukoding.hris.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EmployeeDAO extends BaseDAO<Employee> {
    @Override
    public List<Predicate> predicates(Employee param, CriteriaBuilder builder, Root<Employee> root, boolean isCount) {
        List<Predicate> predicates = super.predicates(param, builder, root, isCount);

        if (param != null) {
            if (param.getUser() != null) {
                predicates.add(builder.like(root.get("user_id"), "%" + param.getUser() + "%"));
            }
        }

        if (param != null) {
            if (param.getNip() != null) {
                predicates.add(builder.like(root.get("nip"), "%" + param.getNip() + "%"));
            }
        }

        if (param != null) {
            if (param.getStatus() != null) {
                predicates.add(builder.like(root.get("status_employee"), "%" + param.getStatus() + "%"));
            }
        }

        if (param != null) {
            if (param.getStatus() != null) {
                predicates.add(builder.equal(root.get("status"), param.getStatus()));
            }
        }

        return predicates;
    }
}
