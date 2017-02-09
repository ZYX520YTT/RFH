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
    private static String getUrl(String activity){
        String url=null;
        url=Url+activity;
        return url;
    }

    /**用户注册**/
    public static String register =getUrl("base/proprietor/register");

    /**获取所有公寓**/
    public static String apartment=getUrl("apartment/list");

    /**用户登录**/
    public static  String login=getUrl("base/login");

    /**业主重置帐号密码**/
    public static String pwdreset=getUrl("proprietor/pwdreset");

    /**业主重置帐号密码-信息验证**/
    public static String pwdreset_validate=getUrl("proprietor/pwdreset/validate");


    /**获取组织机构树**/
    public static String sys_org=getUrl("organization/list");

    /**获取角色列表**/
    public static String sys_role=getUrl("sys/list/role");


}
