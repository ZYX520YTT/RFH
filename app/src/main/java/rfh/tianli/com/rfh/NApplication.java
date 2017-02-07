package rfh.tianli.com.rfh;

import android.app.Activity;
import android.app.Application;

import java.util.List;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 9:01.
 * 描述：
 */

public class NApplication extends Application {

    private static NApplication application;
    private List AllAcivity;

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
