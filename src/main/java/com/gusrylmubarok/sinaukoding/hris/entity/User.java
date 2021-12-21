package com.gusrylmubarok.sinaukoding.hris.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter @Setter
@NoArgsConstructor
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 6095174301945997813L;

    public enum Role{
        ROLE_USER,
        ROLE_ADMIN

    }

    @Column(name = "role", columnDefinition = "VARCHAR(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column(name = "profile_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String profileName;

    @Column(name = "username", columnDefinition = "VARCHAR(50)", nullable = false)
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(50)", nullable = false)
    private String password;

    @Column(name = "no_rekening", columnDefinition = "VARCHAR(255)", nullable = false)
    private String noRekening;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone")
    private String phone;

    @Column(name = "religion")
    private String religion;

    @Column(name = "domicile_address")
    private String domicileAddress;

    @Column(name = "residence_address")
    private String residenceAddress;

    @Column(name = "pendidikan_terakhir")
    private String pendidikanTerakhir;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "nama_ibu")
    private String namaIbu;

    @Column(name = "no_bpjs_ketenagakerjaan")
    private String noBpjsKetenagakerjaan;

    @Column(name = "no_bpjs_kesehatan")
    private String noBpjsKesehatan;

    @Column(name = "no_ktp")
    private String noKtp;

    @Column(name = "npwp")
    private String npwp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bankId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
