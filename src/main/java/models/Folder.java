package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="folders")
public class Folder {

    private int id;
    private String title;
    private Owner owner;
    private List<File> files;

    public Folder() {
    }

    public Folder(String title, Owner owner) {
        this.title = title;
        this.owner = owner;
        this.files = files;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name="owner_id", nullable = false)
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @OneToMany(mappedBy = "folder", fetch = FetchType.EAGER)
    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}