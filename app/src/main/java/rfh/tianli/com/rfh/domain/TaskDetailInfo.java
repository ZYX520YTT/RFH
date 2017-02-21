package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/21 on 12:56.
 * 描述：
 */

public class TaskDetailInfo {
    private long doingTaskId;
    private long id;
    private String code;
    private String name;
    private String brand;
    private String model;
    private String location;
    private String deviceStatus;
    private String inheritData;
    private Integer serialNumber;
    private String qrCodePath;
    private String typeName;
    private String apartmentName;
    private String systemName;
    private Integer sortNumber;
    private String patrolStatus;

    public TaskDetailInfo(long doingTaskId, String patrolStatus, Integer sortNumber, String systemName, String apartmentName, String typeName, String qrCodePath, Integer serialNumber, String inheritData, String deviceStatus, String location, String model, String brand, String name, String code, long id) {
        this.doingTaskId = doingTaskId;
        this.patrolStatus = patrolStatus;
        this.sortNumber = sortNumber;
        this.systemName = systemName;
        this.apartmentName = apartmentName;
        this.typeName = typeName;
        this.qrCodePath = qrCodePath;
        this.serialNumber = serialNumber;
        this.inheritData = inheritData;
        this.deviceStatus = deviceStatus;
        this.location = location;
        this.model = model;
        this.brand = brand;
        this.name = name;
        this.code = code;
        this.id = id;
    }

    public String getPatrolStatus() {
        return patrolStatus;
    }

    public void setPatrolStatus(String patrolStatus) {
        this.patrolStatus = patrolStatus;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public String getInheritData() {
        return inheritData;
    }

    public void setInheritData(String inheritData) {
        this.inheritData = inheritData;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDoingTaskId() {
        return doingTaskId;
    }

    public void setDoingTaskId(long doingTaskId) {
        this.doingTaskId = doingTaskId;
    }

    @Override
    public String toString() {
        return "TaskDetailInfo{" +
                "doingTaskId=" + doingTaskId +
                ", id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", location='" + location + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", inheritData='" + inheritData + '\'' +
                ", serialNumber=" + serialNumber +
                ", qrCodePath='" + qrCodePath + '\'' +
                ", typeName='" + typeName + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", systemName='" + systemName + '\'' +
                ", sortNumber=" + sortNumber +
                ", patrolStatus='" + patrolStatus + '\'' +
                '}';
    }
}
