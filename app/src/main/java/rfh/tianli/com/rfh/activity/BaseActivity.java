package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.os.Bundle;

import rfh.tianli.com.rfh.NApplication;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 9:07.
 * 描述：基类Activity
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NApplication)this.getApplication()).addActivity(this);
    }
}
