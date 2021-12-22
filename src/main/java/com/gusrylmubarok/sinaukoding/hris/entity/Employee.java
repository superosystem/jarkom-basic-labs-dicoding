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

    private static final long serialVersionUID = 7352682923729393109L;

    public enum StatusEmployee {
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
    private StatusEmployee status = StatusEmployee.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
