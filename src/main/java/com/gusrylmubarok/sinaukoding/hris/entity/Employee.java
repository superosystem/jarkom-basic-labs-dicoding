package com.gusrylmubarok.sinaukoding.hris.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@Getter @Setter
@NoArgsConstructor
public class Employee extends BaseEntity<Employee> {

    private static final long serialVersionUID = 139236470948290645L;

    public enum StatusEmpolyee {
        ACTIVE,
        INACTIVE
    }

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "nip")
    private String nip;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEmpolyee statusEmpolyee = StatusEmpolyee.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
