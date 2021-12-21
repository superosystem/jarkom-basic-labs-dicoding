package com.gusrylmubarok.sinaukoding.hris.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attendance")
@Getter @Setter
@NoArgsConstructor
public class Attendance extends BaseEntity<Attendance> {

    private static final long serialVersionUID = -7929292514760448211L;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "rest_start_time")
    @Temporal(TemporalType.TIME)
    private Date restStartTime;

    @Column(name = "rest_end_time")
    @Temporal(TemporalType.TIME)
    private Date restEndTime;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
