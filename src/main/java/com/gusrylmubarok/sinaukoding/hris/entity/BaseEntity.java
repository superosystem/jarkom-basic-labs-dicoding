package com.gusrylmubarok.sinaukoding.hris.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@MappedSuperclass
@DynamicUpdate
@SuppressWarnings("unchecked")
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 2428620307308756675L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @Column(name = "deleted_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedTime;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    @Column(name = "created_by", columnDefinition = "VARCHAR(100)")
    private String createdBy;

    @Column(name = "deleted_by", columnDefinition = "VARCHAR(100)")
    private String deletedBy;

    @Column(name = "updated_by", columnDefinition = "VARCHAR(100)")
    private String updatedBy;

    @PrePersist
    protected void onCreate(){
        setCreatedTime(new Date());
    }

    @PreRemove
    protected void onRemove(){
        setDeleted(Boolean.TRUE);
        setDeletedTime(new Date());
    }

    @PreUpdate
    protected void onUpdate(){
        setUpdatedTime(new Date());
    }




}
