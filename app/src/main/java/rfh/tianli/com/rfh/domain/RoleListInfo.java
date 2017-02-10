package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/10 on 9:41.
 * 描述：角色列表信息
 */

public class RoleListInfo {

    private long id;
    private String name;
    private String code;
    private String description;

    public RoleListInfo(long id, String description, String code, String name) {
        this.id = id;
        this.description = description;
        this.code = code;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "RoleListInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
