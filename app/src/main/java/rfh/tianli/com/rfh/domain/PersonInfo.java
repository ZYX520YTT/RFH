package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/16 on 15:10.
 * 描述：
 */

public class PersonInfo {
    private String proprietorInfomobile;
    private String proprietorInforealName;
    private String proprietorInfosex;
    private String apartmentname;
    private String apartmentaddress;
    private String unitname;
    private String roomNumber;

    private String employeeInfomobile;
    private String employeeInforealName;
    private String organizationname;
    private String roleListname;

    public PersonInfo(String proprietorInfomobile, String roleListname, String organizationname, String employeeInforealName, String employeeInfomobile, String roomNumber, String unitname, String apartmentaddress, String apartmentname, String proprietorInfosex, String proprietorInforealName) {
        this.proprietorInfomobile = proprietorInfomobile;
        this.roleListname = roleListname;
        this.organizationname = organizationname;
        this.employeeInforealName = employeeInforealName;
        this.employeeInfomobile = employeeInfomobile;
        this.roomNumber = roomNumber;
        this.unitname = unitname;
        this.apartmentaddress = apartmentaddress;
        this.apartmentname = apartmentname;
        this.proprietorInfosex = proprietorInfosex;
        this.proprietorInforealName = proprietorInforealName;
    }

    public String getProprietorInfomobile() {
        return proprietorInfomobile;
    }

    public void setProprietorInfomobile(String proprietorInfomobile) {
        this.proprietorInfomobile = proprietorInfomobile;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getRoleListname() {
        return roleListname;
    }

    public void setRoleListname(String roleListname) {
        this.roleListname = roleListname;
    }

    public String getEmployeeInforealName() {
        return employeeInforealName;
    }

    public void setEmployeeInforealName(String employeeInforealName) {
        this.employeeInforealName = employeeInforealName;
    }

    public String getEmployeeInfomobile() {
        return employeeInfomobile;
    }

    public void setEmployeeInfomobile(String employeeInfomobile) {
        this.employeeInfomobile = employeeInfomobile;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getApartmentaddress() {
        return apartmentaddress;
    }

    public void setApartmentaddress(String apartmentaddress) {
        this.apartmentaddress = apartmentaddress;
    }

    public String getApartmentname() {
        return apartmentname;
    }

    public void setApartmentname(String apartmentname) {
        this.apartmentname = apartmentname;
    }

    public String getProprietorInfosex() {
        return proprietorInfosex;
    }

    public void setProprietorInfosex(String proprietorInfosex) {
        this.proprietorInfosex = proprietorInfosex;
    }

    public String getProprietorInforealName() {
        return proprietorInforealName;
    }

    public void setProprietorInforealName(String proprietorInforealName) {
        this.proprietorInforealName = proprietorInforealName;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "proprietorInfomobile='" + proprietorInfomobile + '\'' +
                ", proprietorInforealName='" + proprietorInforealName + '\'' +
                ", proprietorInfosex='" + proprietorInfosex + '\'' +
                ", apartmentname='" + apartmentname + '\'' +
                ", apartmentaddress='" + apartmentaddress + '\'' +
                ", unitname='" + unitname + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", employeeInfomobile='" + employeeInfomobile + '\'' +
                ", employeeInforealName='" + employeeInforealName + '\'' +
                ", organizationname='" + organizationname + '\'' +
                ", roleListname='" + roleListname + '\'' +
                '}';
    }
}
