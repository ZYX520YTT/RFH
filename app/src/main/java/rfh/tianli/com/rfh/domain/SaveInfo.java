package rfh.tianli.com.rfh.domain;

import java.util.List;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/3/2 on 21:01.
 * 描述：
 */

public class SaveInfo {

    private  long taskId;
    private String deviceId;
    private List<PatrolInfo> data;

    public SaveInfo(long taskId, List<PatrolInfo> data, String deviceId) {
        this.taskId = taskId;
        this.data = data;
        this.deviceId = deviceId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public List<PatrolInfo> getPatrolInfoList() {
        return data;
    }

    public void setPatrolInfoList(List<PatrolInfo> patrolInfoList) {
        this.data = patrolInfoList;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "SaveInfo{" +
                "taskId=" + taskId +
                ", deviceId='" + deviceId + '\'' +
                ", PatrolInfo=" + data +
                '}';
    }
}
