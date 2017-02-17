package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/17 on 11:13.
 * 描述：
 */

public class DeviceInfo {

    private String brand;
    private String code;
    private String location;
    private String model;
    private String name;
    private String author;
    private String project;

    public DeviceInfo(String brand, String project, String author, String name, String model, String location, String code) {
        this.brand = brand;
        this.project = project;
        this.author = author;
        this.name = name;
        this.model = model;
        this.location = location;
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "brand='" + brand + '\'' +
                ", code='" + code + '\'' +
                ", location='" + location + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", project='" + project + '\'' +
                '}';
    }
}
