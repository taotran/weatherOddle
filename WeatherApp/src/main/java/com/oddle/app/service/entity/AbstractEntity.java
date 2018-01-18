package com.oddle.app.service.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by tao.tran on 12/14/17.
 */
@MappedSuperclass
public class AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dateCreated")
    private ZonedDateTime dateCreated;

    @Column(name = "dateUpdated")
    private ZonedDateTime dateUpdated;

    @PrePersist
    public void prePersist() {
        if (dateCreated == null) {
            dateCreated = ZonedDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        dateUpdated = ZonedDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ZonedDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(ZonedDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
