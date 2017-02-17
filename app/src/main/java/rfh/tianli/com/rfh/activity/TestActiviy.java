package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.os.Bundle;

import rfh.tianli.com.rfh.R;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/17 on 16:22.
 * 描述：测试的Activity，专为界面测试设计
 */

public class TestActiviy extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_type_datetime);
    }
}
