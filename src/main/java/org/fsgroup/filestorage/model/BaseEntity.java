package org.fsgroup.filestorage.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    public final int getId() {
        return id;
    }

    public final Date getCreated() {
        return created;
    }

    public final Date getUpdated() {
        return updated;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id == ((BaseEntity) o).id;
    }

    @Override
    public final int hashCode() {
        return id;
    }
}
