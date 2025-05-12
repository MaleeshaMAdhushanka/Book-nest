package lk.sliit.booknest.entity;

import java.util.ArrayList;
import java.util.List;

public class Admin {

    private String username;

    private String password;

    private String imgUrl;

    private List<Branch> branch = new ArrayList<>();

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
