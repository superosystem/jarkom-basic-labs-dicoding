package com.gusrylmubarok.sinaukoding.hris.dao;

import com.gusrylmubarok.sinaukoding.hris.entity.Company;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CompanyDAO extends BaseDAO<Company> {
    @Override
    public List<Predicate> predicates(Company param, CriteriaBuilder builder, Root<Company> root, boolean isCount) {
        List<Predicate> predicates = super.predicates(param, builder, root, isCount);

        if (param != null) {
            if (param.getName() != null) {
                predicates.add(builder.like(root.get("name"), "%" + param.getName() + "%"));
            }
        }

        if (param != null) {
            if (param.getPhone() != null) {
                predicates.add(builder.like(root.get("phone"), "%" + param.getPhone() + "%"));
            }
        }

        if (param != null) {
            if (param.getAddress() != null) {
                predicates.add(builder.like(root.get("address"), "%" + param.getAddress() + "%"));
            }
        }

        return predicates;
    }
    public Company findByName(Company param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Company> query = builder.createQuery(Company.class);

        Root<Company> root = query.from(Company.class);

        Predicate usernamePredicate = builder.equal(root.get("name"), param.getName());
        query.where(usernamePredicate);

        TypedQuery<Company> result = entityManager.createQuery(query);
        List<Company> resultList = result.getResultList();

        return resultList.size() > 0 ? resultList.get(0) : new Company();
    }

}
