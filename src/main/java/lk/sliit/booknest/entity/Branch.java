package lk.sliit.booknest.entity;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String branchID;
    private String branchName;
    private String branchAddress;
    private Admin admin;
    private List<Book> books = new ArrayList<>();

    public Branch() {
    }

    public Branch(String branchID, String branchName, String branchAddress, Admin admin) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.admin = admin;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchID='" + branchID + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", admin=" + admin +
                '}';
    }
}
