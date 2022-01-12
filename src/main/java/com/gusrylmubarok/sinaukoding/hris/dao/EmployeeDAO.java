package com.gusrylmubarok.sinaukoding.hris.dao;

import com.gusrylmubarok.sinaukoding.hris.entity.Employee;
import com.gusrylmubarok.sinaukoding.hris.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

        if (!isCount) {
            root.fetch("user", JoinType.INNER).fetch("bank", JoinType.INNER);
            root.fetch("user",JoinType.INNER).fetch("company", JoinType.INNER);
            root.fetch("user",JoinType.INNER).fetch("position", JoinType.INNER);
            root.fetch("user",JoinType.INNER).fetch("division", JoinType.INNER);
        }

        return predicates;
    }

    public Employee findByUserId(User param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);

        Root<Employee> root = query.from(Employee.class);

        Predicate userPredicate = builder.equal(root.get("user").get("id"), param.getId());
        query.where(userPredicate);

        root.fetch("user",JoinType.INNER).fetch("bank", JoinType.INNER);
        root.fetch("user",JoinType.INNER).fetch("company", JoinType.INNER);
        root.fetch("user",JoinType.INNER).fetch("position", JoinType.INNER);
        root.fetch("user",JoinType.INNER).fetch("division", JoinType.INNER);

        TypedQuery<Employee> result = entityManager.createQuery(query);
        List<Employee> resultList = result.getResultList();

        return resultList.size() > 0 ? resultList.get(0) : new Employee();
    }
}
