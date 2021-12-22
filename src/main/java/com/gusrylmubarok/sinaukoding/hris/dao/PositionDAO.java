package com.gusrylmubarok.sinaukoding.hris.dao;

import com.gusrylmubarok.sinaukoding.hris.entity.Position;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PositionDAO extends BaseDAO<Position> {
    @Override
    public List<Predicate> predicates(Position param, CriteriaBuilder builder, Root<Position> root, boolean isCount) {
        List<Predicate> predicates = super.predicates(param, builder, root, isCount);

        if (param != null) {
            if (param.getName() != null) {
                predicates.add(builder.like(root.get("name"), "%" + param.getName() + "%"));
            }
        }

        if (param != null) {
            if (param.getNote() != null) {
                predicates.add(builder.like(root.get("note"), "%" + param.getNote() + "%"));
            }
        }

        return predicates;
    }
}
