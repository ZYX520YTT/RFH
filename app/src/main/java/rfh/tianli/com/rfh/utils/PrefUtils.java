package rfh.tianli.com.rfh.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/6 on 18:49.
 * 描述：
 */

public class PrefUtils {
    public static final String PREF_NAME = "config";

    public static final  String ISFIRST="FIRST";

    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }


    public static String getString(Context ctx, String key, String defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void setString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    //设置是否是第一次进入APP
    public static void setIsFirst(Context context){
        setBoolean(context,ISFIRST,true);
    }
    //获取是否是第一次进入APP
    public static Boolean getIsFirst(Context context){
        return getBoolean(context,ISFIRST,false);//默认没有进入过APP
    }


}
