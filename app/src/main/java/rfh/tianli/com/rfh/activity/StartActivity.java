package rfh.tianli.com.rfh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import rfh.tianli.com.rfh.MainActivity;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;
import rfh.tianli.com.rfh.thread.User;

public class StartActivity extends BaseActivity {


    private Context context;
    private AsyncHttpResponseHandler login_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        context=this;
        dohandler();
        Init();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            User user=new User(context);
            String name=user.getUserNumber();
            String pwd=user.getPass();
            if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pwd)){
                RequestParams params=new RequestParams();
                params.put("name",name);
                params.put("password",pwd);
                HttpUtils.post(context, Url.login,params,login_handler);
            }else{
                startActivity(new Intent(StartActivity.this,LoginActivity.class));
                finish();
            }
        }
    };

    public void Init(){
        handler.sendEmptyMessageDelayed(0,1000);//启动页默认一秒
    }


    private void dohandler(){
        login_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String result=jsonObject.getString("result");
                    if(result.equals("success")){
                        startActivity(new Intent(StartActivity.this,MainActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(StartActivity.this,LoginActivity.class));
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "网络连接错误，请检查网络设置后重试。", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StartActivity.this,LoginActivity.class));
                finish();
            }
        };
    }
}
