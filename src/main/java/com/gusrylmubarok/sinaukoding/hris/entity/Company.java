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
@Table(name = "company")
@Getter @Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE company SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Company extends BaseEntity<Company> {

   private static final long serialVersionUID = 3174659189884587820L;

   @Column(name = "name", columnDefinition = "VARCHAR(100)")
   private String name;

   @Column(name = "phone", columnDefinition = "VARCHAR(30)")
   private String phone;

   @Column(name = "address", columnDefinition = "VARCHAR(100)")
   private String address;

   @Column(name = "latitude")
   private Double latitude;

   @Column(name = "longitude")
   private Double longitude;
}
