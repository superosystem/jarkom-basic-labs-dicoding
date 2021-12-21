package com.gusrylmubarok.sinaukoding.hris.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
@Getter @Setter
@NoArgsConstructor
public class Bank extends BaseEntity<Bank> {

    private static final long serialVersionUID = -6490171810504458077L;

    @Column(name = "code", columnDefinition = "VARCHAR(10)", nullable = false)
    private String code;

    @Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
    private String name;

}
