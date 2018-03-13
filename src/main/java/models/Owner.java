package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="owners")
public class Owner {

    private int id;
    private String name;
    private String userName;
    private Set<Folder> folders;

    public Owner() {
    }

    public Owner(String name, String userName) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.folders = folders;
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

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Set<Folder> folders) {
        this.folders = folders;
    }

    //    public void giveRaise() {
//        for (File dude : this.employees) {
//            int raise = (int)(dude.getSalary() * 0.1);
//            dude.setSalary(raise + dude.getSalary());
//            this.budget -= raise;
//            DBHelper.update(dude);
//        }
//    }
}
