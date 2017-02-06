package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import rfh.tianli.com.rfh.R;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Init();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(StartActivity.this,LoginActivity.class));
        }
    };

    public void Init(){
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
