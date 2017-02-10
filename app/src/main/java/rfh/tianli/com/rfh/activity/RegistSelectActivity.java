package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import rfh.tianli.com.rfh.R;

public class RegistSelectActivity extends Activity {

    private Context context;

    /********
     * 返回
     *********/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;

    @ViewInject(R.id.btn_kh)//客户注册
    private Button btn_kh;
    @ViewInject(R.id.btn_yg)//员工注册
    private Button btn_yg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_select);
        context=this;
        ViewUtils.inject(this);
        Init();
    }

    private void Init(){

        //对返回按钮的监听
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.out_right_in, R.anim.out_left_out);
            }
        });
        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.out_right_in, R.anim.out_left_out);
            }
        });

        //对"客户注册"按钮监听
        btn_kh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistSelectActivity.this,RegistActivity.class));
                finish();
                overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
            }
        });

        //对"员工注册"按钮监听
        btn_yg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistSelectActivity.this,RegistByYgActivity.class));
                finish();
                overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
            }
        });
    }


    //直接点击返回按钮
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
    }


}
