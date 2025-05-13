package lk.sliit.booknest.dto;

public class BranchDto {

    private String branchID;

    private String branchName;

    private String address;

    private String adminID;

    public BranchDto() {
    }

    public BranchDto(String branchID, String branchName, String address, String adminID) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.address = address;
        this.adminID = adminID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "BranchDto{" +
                "branchID='" + branchID + '\'' +
                ", branchName='" + branchName + '\'' +
                ", address='" + address + '\'' +
                ", adminID='" + adminID + '\'' +
                '}';
    }
}
