package rfh.tianli.com.rfh;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rfh.tianli.com.rfh.utils.Tools;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 9:01.
 * 描述：
 */

public class NApplication extends Application {

    private static NApplication application;
    private static int mainTid;
    private static Handler handler;
    private List AllAcivity;

    public static String user_power;//用户权限


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Tools.getImage(this);
        AllAcivity=new ArrayList<Map<String,String>>();
    }

    public static Context getApplication() {
        return application;
    }
    public static int getMainTid() {
        return mainTid;
    }
    public static Handler getHandler() {
        return handler;
    }

    public void addActivity(Activity activity) {
        try {
            AllAcivity.add(activity);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void destoryAllActivity() {
        try {
            for (int i = 0; i < AllAcivity.size(); i++) {
                ((Activity) AllAcivity.get(i)).finish();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
