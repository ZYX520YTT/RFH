package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/21 on 10:53.
 * 描述：
 */

public class TaskInfo {

    private String apartmentName;
    private String creatorName;
    private long executeTime;
    private String executorName;
    private long id;
    private Integer timeLimit;
    private String name;
    private String status;
    private String type;
    private String needRemark;
    private long startTime;
    private long endTime;
    private String remarks;
    private Integer timeTaking;

    public TaskInfo(String apartmentName, Integer timeTaking, String remarks, long endTime, long startTime, String needRemark, String type, String status, String name, Integer timeLimit, long id, String executorName, long executeTime, String creatorName) {
        this.apartmentName = apartmentName;
        this.timeTaking = timeTaking;
        this.remarks = remarks;
        this.endTime = endTime;
        this.startTime = startTime;
        this.needRemark = needRemark;
        this.type = type;
        this.status = status;
        this.name = name;
        this.timeLimit = timeLimit;
        this.id = id;
        this.executorName = executorName;
        this.executeTime = executeTime;
        this.creatorName = creatorName;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public Integer getTimeTaking() {
        return timeTaking;
    }

    public void setTimeTaking(Integer timeTaking) {
        this.timeTaking = timeTaking;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getNeedRemark() {
        return needRemark;
    }

    public void setNeedRemark(String needRemark) {
        this.needRemark = needRemark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "apartmentName='" + apartmentName + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", executeTime=" + executeTime +
                ", executorName='" + executorName + '\'' +
                ", id=" + id +
                ", timeLimit=" + timeLimit +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", needRemark='" + needRemark + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", remarks='" + remarks + '\'' +
                ", timeTaking=" + timeTaking +
                '}';
    }
}
