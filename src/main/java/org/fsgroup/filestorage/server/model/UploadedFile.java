package org.fsgroup.filestorage.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "filestorage_uploadedfile")
public class UploadedFile extends BaseEntity {

    @Column(unique = false, nullable = false, updatable = false)
    private String name;

    @Column(unique = false, nullable = false, updatable = false)
    private String size;

    @Column(unique = true, nullable = false, updatable = false)
    private String path;

    public UploadedFile() {
    }

    public UploadedFile(String name, String size, String path) {
        this.name = name;
        this.size = size;
        this.path = path;
    }

    public String path() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }
}
