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
@Table(name = "bank")
@Getter @Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE bank SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Bank extends BaseEntity<Bank> {

    private static final long serialVersionUID = -6490171810504458077L;

    @Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
    private String name;

    @Column(name = "code", columnDefinition = "VARCHAR(10)", nullable = false)
    private String code;
}
