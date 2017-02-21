package rfh.tianli.com.rfh.thread;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 10:02.
 * 描述：Url信息类
 */

public class Url {

    public final  static String Url="https://tianli.qjun.com.cn:8443/";

    //https://tianli.qjun.com.cn:8443/base/proprietor/register?mobile=12345678910&password=123456&apartmentId=12&roomNumber=13

    //富力桃园1单元1001
    //https://tianli.qjun.com.cn:8443/apartment/list
    ////https://tianli.qjun.com.cn:8443/apartment/unitList?id=1
    //https://tianli.qjun.com.cn:8443/apartment/unit/doorList?id=2
    //https://tianli.qjun.com.cn:8443/sys/list/role
    //https://tianli.qjun.com.cn:8443/sys/list/org
    //https://tianli.qjun.com.cn:8443/sys/authentic/info

    //https://tianli.qjun.com.cn:8443/patrol/task/undo
    private static String getUrl(String activity){
        String url=null;
        url=Url+activity;
        return url;
    }

    /**用户注册**/
    public static String register =getUrl("prop/proprietor/register");

    /**公司员工注册**/
    public static String employee_register=getUrl("sys/employee/register");

    /**获取所有公寓**/
    public static String apartment=getUrl("prop/apartment/list");

    /**用户登录**/
    public static  String login=getUrl("sys/base/login");

    /**业主重置帐号密码**/
    public static String pwdreset=getUrl("prop/proprietor/pwdreset");

    /**业主重置帐号密码-信息验证**/
    public static String pwdreset_validate=getUrl("prop/proprietor/pwdreset/validate");


    /**获取组织机构列表**/
    public static String sys_org=getUrl("sys/organization/list");

    /**获取角色列表**/
    public static String sys_role=getUrl("sys/role/list");

    /**用户登录后获取详细信息**/
    public static String sys_info=getUrl("sys/authentic/info");

    /**注销登录***/
    public static String logout=getUrl("sys/authentic/logout");


    /**获取设备信息***/
    public static String device_info=getUrl("res/device/info");

    /**获取当前登录用户待做巡检任务列表***/
    public static String task_undo=getUrl("/patrol/task/undo");

    /**开始一个巡查任务***/
    public static String task_start=getUrl("/patrol/task/start");

    /**二维码巡查**/
    public static String patrol_qr=getUrl("/patrol/patrol/qr");



}
