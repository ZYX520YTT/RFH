package rfh.tianli.com.rfh;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import rfh.tianli.com.rfh.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private Boolean isExit=false;//判断是否要退出程序
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        if(isExit){
            super.onBackPressed();
            ((NApplication)this.getApplication()).destoryAllActivity();
            finish();
        }else{
            isExit=true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit=false;
                }
            },2000);
            Toast.makeText(context,"再点击一次退出程序",Toast.LENGTH_SHORT).show();
        }

    }
}
