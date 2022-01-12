package com.gusrylmubarok.sinaukoding.hris.dao;

import com.gusrylmubarok.sinaukoding.hris.entity.Bank;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BankDAO extends BaseDAO<Bank> {
    @Override
    public List<Predicate> predicates(Bank param, CriteriaBuilder builder, Root<Bank> root, boolean isCount) {
        List<Predicate> predicates = super.predicates(param, builder, root, isCount);

        if (param != null) {
            if (param.getName() != null) {
                predicates.add(builder.like(root.get("name"), "%" + param.getName() + "%"));
            }
        }

        if (param != null) {
            if (param.getCode() != null) {
                predicates.add(builder.like(root.get("code"), "%" + param.getCode()+ "%"));
            }
        }

        return predicates;
    }

    public Bank findByName(Bank param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bank> query = builder.createQuery(Bank.class);

        Root<Bank> root = query.from(Bank.class);

        Predicate usernamePredicate = builder.equal(root.get("name"), param.getName());
        query.where(usernamePredicate);

        TypedQuery<Bank> result = entityManager.createQuery(query);
        List<Bank> resultList = result.getResultList();

        return resultList.size() > 0 ? resultList.get(0) : new Bank();
    }
}
