package rfh.tianli.com.rfh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.thread.User;

public class StartActivity extends BaseActivity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        context=this;
        Init();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            User user=new User(context);
            String name=user.getPhone();
            String pwd=user.getPass();
//            if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pwd)){
//                NApplication.user_power=user.getUserPower();
//                startActivity(new Intent(StartActivity.this,MainActivity.class));
//                finish();
//            }else{
//                startActivity(new Intent(StartActivity.this,LoginActivity.class));
//                finish();
//            }
            startActivity(new Intent(StartActivity.this,LoginActivity.class));
            finish();
        }
    };

    public void Init(){
        handler.sendEmptyMessageDelayed(0,1000);//启动页默认一秒
    }
}
