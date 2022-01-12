package com.gusrylmubarok.sinaukoding.hris.dao;

import com.gusrylmubarok.sinaukoding.hris.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UserDAO extends BaseDAO<User> {
    @Override
    public List<Predicate> predicates(User param, CriteriaBuilder builder, Root<User> root, boolean isCount) {
        List<Predicate> predicates = super.predicates(param, builder, root, isCount);

        if (param != null) {
            if (param.getUsername() != null) {
                predicates.add(builder.equal(root.get("username"), param.getUsername()));
            }
        }

        if (param != null) {
            if (param.getRole() != null) {
                predicates.add(builder.equal(root.get("role"), param.getRole()));
            }
        }

        if (param.getProfileName() != null) {
            predicates.add(builder.like(root.get("profileName"), "%" + param.getProfileName() + "%"));
        }

        if (param != null) {
            if (param.getNickName() != null) {
                predicates.add(builder.equal(root.get("nickname"), param.getNickName()));
            }
        }

        if (param != null) {
            if (param.getReligion() != null) {
                predicates.add(builder.equal(root.get("religion"), param.getReligion()));
            }
        }

        if (!isCount) {
            root.fetch("bank", JoinType.INNER);
            root.fetch("company", JoinType.INNER);
            root.fetch("position", JoinType.INNER);
            root.fetch("division", JoinType.INNER);
        }

        return predicates;
    }

    public User findByUsername(User param) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);

        Root<User> root = query.from(User.class);

        Predicate usernamePredicate = builder.equal(root.get("username"), param.getUsername());
        query.where(usernamePredicate);

        root.fetch("bank", JoinType.INNER);
        root.fetch("company", JoinType.INNER);
        root.fetch("position", JoinType.INNER);
        root.fetch("division", JoinType.INNER);

        TypedQuery<User> result = entityManager.createQuery(query);
        List<User> resultList = result.getResultList();

        return resultList.size() > 0 ? resultList.get(0) : new User();
    }
}
