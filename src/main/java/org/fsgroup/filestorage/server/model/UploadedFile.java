package org.fsgroup.filestorage.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "filestorage_uploadedfile")
public class UploadedFile extends BaseEntity {

    @Column(unique = false, nullable = false, updatable = true)
    private String name;

    @Column(unique = true, nullable = false, updatable = false)
    private String path;

    public UploadedFile() {
    }

    public UploadedFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String path() {
        return path;
    }

    public String getName() {
        return name;
    }
}
