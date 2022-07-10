package com.jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BasicEntityColumn {

    @Column(name = "created_dt")
    protected ZonedDateTime createdDt;
    @Column(name = "updated_dt")
    protected ZonedDateTime updatedDt;

    @PrePersist
    void setCreate(){
        this.createdDt = ZonedDateTime.now();
        this.updatedDt = ZonedDateTime.now();
    }

    @PreUpdate
    void setUpdate(){
        this.updatedDt = ZonedDateTime.now();
    }
}
