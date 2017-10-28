package org.fsgroup.filestorage.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "filestorage_user")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(unique = false, nullable = false, updatable = true)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    private List<UploadedFile> files;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.files = new ArrayList<>();
    }

    public void addFile(UploadedFile file) {
        files.add(file);
    }

    public void removeFile(UploadedFile file) {
        files.remove(file);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasFile(int id) {
        return files.stream().anyMatch(file -> file.getId() == id);
    }

    public String password() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<UploadedFile> getFiles() {
        return files;
    }
}
