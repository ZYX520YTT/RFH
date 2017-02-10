package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/10 on 9:42.
 * 描述：部门列表信息
 */

public class OrgListInfo {
    private long id;
    private String name;
    private String type;

    public OrgListInfo(long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrgListInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
