package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/17 on 15:47.
 * 描述：
 */

public class DeviceDataInfo {
    private String code;
    private String dataType;
    private String description;
    private long id;
    private String name;
    private Integer sortNumber;
    private String unit;
    private String widgetType;
    private String status;
    private long recordTime;
    private String value;

    public DeviceDataInfo(String code, String value, long recordTime, String status, String widgetType, String unit, Integer sortNumber, String name, long id, String description, String dataType) {
        this.code = code;
        this.value = value;
        this.recordTime = recordTime;
        this.status = status;
        this.widgetType = widgetType;
        this.unit = unit;
        this.sortNumber = sortNumber;
        this.name = name;
        this.id = id;
        this.description = description;
        this.dataType = dataType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(long recordTime) {
        this.recordTime = recordTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "DeviceDataInfo{" +
                "code='" + code + '\'' +
                ", dataType='" + dataType + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sortNumber=" + sortNumber +
                ", unit='" + unit + '\'' +
                ", widgetType='" + widgetType + '\'' +
                ", status='" + status + '\'' +
                ", recordTime=" + recordTime +
                ", value='" + value + '\'' +
                '}';
    }
}
