package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/23 on 10:23.
 * 描述：
 */

public class PatrolInfo {
    private long id;
    private String value;

    public PatrolInfo(long id,String value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PatrolInfo{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
