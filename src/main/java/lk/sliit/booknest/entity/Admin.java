package lk.sliit.booknest.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "username",length = 30)
    private String username;

    @Column(name = "password")
    private String password;

    private String imgUrl;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER ,mappedBy = "admin")
    private List<Branch> branch= new ArrayList<>();


    public Admin() {
    }

    public Admin(String username, String password, String imgUrl) {
        this.username = username;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Branch> getBranch() {
        return branch;
    }

    public void setBranch(List<Branch> branch) {
        this.branch = branch;
    }

}
