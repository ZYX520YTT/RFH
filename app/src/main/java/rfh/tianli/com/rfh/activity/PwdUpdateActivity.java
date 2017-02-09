package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;

public class PwdUpdateActivity extends Activity {

    private Context context;

    private AsyncHttpResponseHandler confrim_handler;

    /********
     * 返回
     *********/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;

    @ViewInject(R.id.et_pwd1)//新密码
    private EditText et_pwd1;
    @ViewInject(R.id.et_pwd2)//确认密码
    private EditText et_pwd2;

    @ViewInject(R.id.btn_confirm)
    private Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_update);
        context = this;
        ViewUtils.inject(this);
        dohandler();
        Init();
    }

    private void Init() {
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

        //对"确认"按钮的监听
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInof()){
                    PwdUpdateInfo();
                }
            }
        });
    }

    private void PwdUpdateInfo(){
        RequestParams params=new RequestParams();
        params.put("newPassword",et_pwd1.getText().toString());
        HttpUtils.post(context, Url.pwdreset,params,confrim_handler);
    }


    //检查输入信息
    private Boolean CheckInof(){
        String pwd1=et_pwd1.getText().toString();
        String pwd2=et_pwd2.getText().toString();
        if(TextUtils.isEmpty(pwd1)){
            Toast.makeText(context,"新密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pwd2)){
            Toast.makeText(context,"确认密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pwd1.equals(pwd2)){
            Toast.makeText(context,"两次输入的密码不同!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void dohandler(){

        confrim_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String result=jsonObject.getString("result");
                    if(result.equals("success")){
                        finish();
                        overridePendingTransition(R.anim.out_right_in, R.anim.out_left_out);
                    }
                    String message=jsonObject.getString("message");
                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "网络连接错误，请检查网络设置后重试。", Toast.LENGTH_SHORT).show();
            }
        };
    }

    //直接点击返回按钮
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
    }
}
