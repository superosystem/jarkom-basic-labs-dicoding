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

    @Column(name = "role", columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column(name = "profile_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String profileName;

    @Column(name = "username", columnDefinition = "VARCHAR(50)", nullable = false)
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;

    @Column(name = "no_rekening", columnDefinition = "VARCHAR(255)")
    private String noRekening;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;

    @Column(name = "nick_name", columnDefinition = "VARCHAR(255)")
    private String nickName;

    @Column(name = "place_of_birth", columnDefinition = "VARCHAR(255)")
    private String placeOfBirth;

    @Column(name = "no_bpjs_ketenagakerjaan", columnDefinition = "VARCHAR(255)")
    private String noBpjsKetenagakerjaan;

    @Column(name = "no_bpjs_kesehatan", columnDefinition = "VARCHAR(255)")
    private String noBpjsKesehatan;

    @Column(name = "no_ktp", columnDefinition = "VARCHAR(255)")
    private String noKtp;

    @Column(name = "no_npwp", columnDefinition = "VARCHAR(255)")
    private String npwp;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "marital_status", columnDefinition = "VARCHAR(255)")
    private String maritalStatus;

    @Column(name = "religion", columnDefinition = "VARCHAR(255)")
    private String religion;

    @Column(name = "domicilie_address", columnDefinition = "VARCHAR(255)")
    private String domicileAddress;

    @Column(name = "residence_address", columnDefinition = "VARCHAR(255)")
    private String residenceAddress;

    @Column(name = "pendidikan_terakhir", columnDefinition = "VARCHAR(255)")
    private String pendidikanTerakhir;

    @Column(name = "nama_ibu", columnDefinition = "VARCHAR(255)")
    private String namaIbu;

    @Column(name = "phone", columnDefinition = "VARCHAR(255)")
    private String phone;

    @Column(name = "token")
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="company_id")
    private Company company;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="division_id")
    private Division division;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="position_id")
    private Position position;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="bank_id")
    private Bank bank;

    public User(String username) {
     this.username = username;
    }
}
