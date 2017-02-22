package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/21 on 12:56.
 * 描述：
 */

public class TaskDetailInfo {
    /****任务详细信息**/
    private long taskid;
    private String taskname;
    private String tasktype;
    private Integer tasktimeLimit;
    private String taskneedRemark;
    private String taskapartmentName;
    private String taskstatus;
    private long taskexecuteTime;
    private String taskcreatorName;
    private String taskexecutorName;
    private long taskstartTime;
    private long taskendTime;
    private Integer tasktimeTaking;
    private String taskremarks;

    /***需要巡检的设备列表****/
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

    public TaskDetailInfo(long taskid, String taskstatus, String taskcreatorName, String name, String patrolStatus, Integer sortNumber, String systemName, String apartmentName, String typeName, String qrCodePath, Integer serialNumber, String inheritData, String deviceStatus, String location, String model, String brand, String code, long id, String taskremarks, Integer tasktimeTaking, long taskendTime, long taskstartTime, String taskexecutorName, long taskexecuteTime, String taskapartmentName, Integer tasktimeLimit, String taskneedRemark, String tasktype, String taskname) {
        this.taskid = taskid;
        this.taskstatus = taskstatus;
        this.taskcreatorName = taskcreatorName;
        this.name = name;
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
        this.code = code;
        this.id = id;
        this.taskremarks = taskremarks;
        this.tasktimeTaking = tasktimeTaking;
        this.taskendTime = taskendTime;
        this.taskstartTime = taskstartTime;
        this.taskexecutorName = taskexecutorName;
        this.taskexecuteTime = taskexecuteTime;
        this.taskapartmentName = taskapartmentName;
        this.tasktimeLimit = tasktimeLimit;
        this.taskneedRemark = taskneedRemark;
        this.tasktype = tasktype;
        this.taskname = taskname;
    }

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public String getPatrolStatus() {
        return patrolStatus;
    }

    public void setPatrolStatus(String patrolStatus) {
        this.patrolStatus = patrolStatus;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInheritData() {
        return inheritData;
    }

    public void setInheritData(String inheritData) {
        this.inheritData = inheritData;
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

    public String getTaskremarks() {
        return taskremarks;
    }

    public void setTaskremarks(String taskremarks) {
        this.taskremarks = taskremarks;
    }

    public Integer getTasktimeTaking() {
        return tasktimeTaking;
    }

    public void setTasktimeTaking(Integer tasktimeTaking) {
        this.tasktimeTaking = tasktimeTaking;
    }

    public long getTaskendTime() {
        return taskendTime;
    }

    public void setTaskendTime(long taskendTime) {
        this.taskendTime = taskendTime;
    }

    public long getTaskstartTime() {
        return taskstartTime;
    }

    public void setTaskstartTime(long taskstartTime) {
        this.taskstartTime = taskstartTime;
    }

    public String getTaskexecutorName() {
        return taskexecutorName;
    }

    public void setTaskexecutorName(String taskexecutorName) {
        this.taskexecutorName = taskexecutorName;
    }

    public String getTaskcreatorName() {
        return taskcreatorName;
    }

    public void setTaskcreatorName(String taskcreatorName) {
        this.taskcreatorName = taskcreatorName;
    }

    public long getTaskexecuteTime() {
        return taskexecuteTime;
    }

    public void setTaskexecuteTime(long taskexecuteTime) {
        this.taskexecuteTime = taskexecuteTime;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    public String getTaskapartmentName() {
        return taskapartmentName;
    }

    public void setTaskapartmentName(String taskapartmentName) {
        this.taskapartmentName = taskapartmentName;
    }

    public String getTaskneedRemark() {
        return taskneedRemark;
    }

    public void setTaskneedRemark(String taskneedRemark) {
        this.taskneedRemark = taskneedRemark;
    }

    public Integer getTasktimeLimit() {
        return tasktimeLimit;
    }

    public void setTasktimeLimit(Integer tasktimeLimit) {
        this.tasktimeLimit = tasktimeLimit;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    @Override
    public String toString() {
        return "TaskDetailInfo{" +
                "taskid=" + taskid +
                ", taskname='" + taskname + '\'' +
                ", tasktype='" + tasktype + '\'' +
                ", tasktimeLimit=" + tasktimeLimit +
                ", taskneedRemark='" + taskneedRemark + '\'' +
                ", taskapartmentName='" + taskapartmentName + '\'' +
                ", taskstatus='" + taskstatus + '\'' +
                ", taskexecuteTime=" + taskexecuteTime +
                ", taskcreatorName='" + taskcreatorName + '\'' +
                ", taskexecutorName='" + taskexecutorName + '\'' +
                ", taskstartTime=" + taskstartTime +
                ", taskendTime=" + taskendTime +
                ", tasktimeTaking=" + tasktimeTaking +
                ", taskremarks='" + taskremarks + '\'' +
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
