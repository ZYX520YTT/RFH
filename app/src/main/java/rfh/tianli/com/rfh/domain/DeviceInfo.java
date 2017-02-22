package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/17 on 11:13.
 * 描述：
 */

public class DeviceInfo {

    private String deviceName;
    private String deviceCode;

    public DeviceInfo(String deviceName, String deviceCode) {

        this.deviceName = deviceName;
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "deviceName='" + deviceName + '\'' +
                ", deviceCode='" + deviceCode + '\'' +
                '}';
    }
}
