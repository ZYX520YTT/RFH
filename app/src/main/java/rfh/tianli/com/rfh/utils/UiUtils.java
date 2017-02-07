package rfh.tianli.com.rfh.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

import rfh.tianli.com.rfh.NApplication;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 10:11.
 * 描述：一些对Ui的操作
 */

public class UiUtils {

    /**
     * 获取到字符数组
     * @param tabNames  字符数组的id
     */
    public static String[] getStringArray(int tabNames) {
        return getResource().getStringArray(tabNames);
    }

    public static Resources getResource() {
        return NApplication.getApplication().getResources();
    }
    public static Context getContext(){
        return NApplication.getApplication();
    }
    /** dip转换px */
    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** px转换dip */

    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
    /**
     * 把Runnable 方法提交到主线程运行
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        // 在主线程运行
        if(android.os.Process.myTid()==NApplication.getMainTid()){
            runnable.run();
        }else{
            //获取handler
            NApplication.getHandler().post(runnable);
        }
    }

    /**
     * 在子线程执行任务
     * @param runnable
     */
    public static void runOnThread(Runnable runnable){
        new Thread(runnable){}.start();
    }



    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    public static Drawable getDrawalbe(int id) {
        return getResource().getDrawable(id);
    }

    public static int getDimens(int homePictureHeight) {
        return (int) getResource().getDimension(homePictureHeight);
    }
    /**
     * 延迟执行 任务
     * @param run   任务
     * @param time  延迟的时间
     */
    public static void postDelayed(Runnable run, int time) {
        NApplication.getHandler().postDelayed(run, time); // 调用Runable里面的run方法
    }
    /**
     * 取消任务
     * @param auToRunTask
     */
    public static void cancel(Runnable auToRunTask) {
        NApplication.getHandler().removeCallbacks(auToRunTask);
    }
}
