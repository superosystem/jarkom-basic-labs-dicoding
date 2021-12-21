package com.gusrylmubarok.sinaukoding.hris.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.function.DoubleToLongFunction;

@Entity
@Table(name = "company")
@Getter @Setter
@NoArgsConstructor
public class Company extends BaseEntity<Company> {

    private static final long serialVersionUID = 3174659189884587820L;

    @Column(name = "address", columnDefinition = "VARCHAR(100)", nullable = false)
    private String address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longtitude")
    private Double longtitude;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    @Column(name = "phone", columnDefinition = "VARCHAR(30)")
    private String phone;
}
