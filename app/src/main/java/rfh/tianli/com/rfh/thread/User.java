package rfh.tianli.com.rfh.thread;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/8 on 11:09.
 * 描述：用户手机号码的本地信息的存储和读取
 */

public class User {

    private Context context;

    // 构造方法中传入上下文对象
    public User(Context context) {
        super();
        this.context = context;
    }

    public void savePhone(String userPhone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPhone", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("UserPhone", userPhone); // 目前是保存在内存中，还没有保存到文件中
        editor.commit(); // 数据提交到xml文件中
    }

    public void savePass(String pass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "savePass", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("savePass", pass); // 目前是保存在内存中，还没有保存到文件中
        editor.commit(); // 数据提交到xml文件中
    }

    public void saveUserNumber(String UserNumber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserNumber", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("UserNumber", UserNumber); // 目前是保存在内存中，还没有保存到文件中
        editor.commit(); // 数据提交到xml文件中
    }

    public void saveUserPower(String UserPower) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPower", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString("UserPower", UserPower); // 目前是保存在内存中，还没有保存到文件中
        editor.commit(); // 数据提交到xml文件中
    }

    public String getPhone() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPhone", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserPhone", "");
    }

    public String getPass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "savePass", Context.MODE_PRIVATE);
        return sharedPreferences.getString("savePass", "");
    }

    public String getUserNumber() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserNumber", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserNumber", "");
    }

    public String getUserPower() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPower", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserPower", "");
    }

    public void removePass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "savePass", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.remove("savePass");
        editor.commit();
    }

    public void removeUserNumber() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserNumber", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.remove("UserNumber");
        editor.commit();
    }

    public void removeUserPower() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPower", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.remove("UserPower");
        editor.commit();
    }
}
