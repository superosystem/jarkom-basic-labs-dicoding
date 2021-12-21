package com.gusrylmubarok.sinaukoding.hris.dao;

import com.gusrylmubarok.sinaukoding.hris.entity.Attendance;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AttendanceDAO extends BaseDAO<Attendance> {

    @Override
    public List<Predicate> predicates(Attendance param, CriteriaBuilder builder, Root<Attendance> root, boolean isCount) {
        List<Predicate> predicates = super.predicates(param, builder, root, isCount);

        return predicates;
    }

}
