package com.gusrylmubarok.sinaukoding.hris.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "division")
@Getter @Setter
@NoArgsConstructor
public class Division extends BaseEntity<Division> {

    private static final long serialVersionUID = 8910423892206400642L;

    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
