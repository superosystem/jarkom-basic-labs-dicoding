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

    private static final long serialVersionUID = -1944461090978755097L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
