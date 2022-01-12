package com.gusrylmubarok.sinaukoding.hris.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "position")
@Getter @Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE position SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Position extends BaseEntity<Position> {

    private static final long serialVersionUID = -5571622879579680613L;

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
