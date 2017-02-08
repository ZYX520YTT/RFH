package rfh.tianli.com.rfh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import rfh.tianli.com.rfh.MainActivity;
import rfh.tianli.com.rfh.NApplication;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;
import rfh.tianli.com.rfh.thread.User;

public class LoginActivity extends BaseActivity {

    private Context context;

    private AsyncHttpResponseHandler login_handler;

    @ViewInject(R.id.tv_rg)
    private TextView tv_rg;

    @ViewInject(R.id.et_name)
    private EditText et_name;//用户名
    @ViewInject(R.id.et_pwd)
    private EditText et_pwd;//密码

    @ViewInject(R.id.bt_login)
    private Button bt_login;//登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context=this;
        ViewUtils.inject(this);
        dohandler();
        Init();
    }


    private void Init(){
        //对"立即注册"按钮的监听
        tv_rg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
                overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInfo()){
                    RequestParams params=new RequestParams();
                    params.add("name",et_name.getText().toString());
                    params.add("password",et_pwd.getText().toString());
                    HttpUtils.post(context, Url.login,params,login_handler);
                }
            }
        });
    }

    //检查登录信息是否合格
    private Boolean CheckInfo(){
        String name=et_name.getText().toString();
        String pwd=et_pwd.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(context,"用户名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pwd)){
            Toast.makeText(context,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(name.length()!=11){
            Toast.makeText(context,"用户名格式不正确!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pwd)){
            Toast.makeText(context,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
                        String userType=jsonObject.getString("userType");

                        /*****保存用户权限,账号,密码*******/
                        NApplication.user_power=userType;
                        User user=new User(context);
                        user.saveUserPower(userType);
                        String name=et_name.getText().toString();
                        String pwd=et_pwd.getText().toString();

                        user.savePhone(name);//保存用户名(手机号码)
                        user.savePass(pwd);//保存用户密码

                        /*****保存用户权限*******/

                        Toast.makeText(context,"登录成功!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
                    }else{
                        Toast.makeText(context,"密码错误!",Toast.LENGTH_SHORT).show();
                    }
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


}
