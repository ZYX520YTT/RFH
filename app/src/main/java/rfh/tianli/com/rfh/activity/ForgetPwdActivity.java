package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class ForgetPwdActivity extends Activity {

    private Context context;

    private AsyncHttpResponseHandler find_hanlder;

    /********
     * 返回
     *********/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;

    @ViewInject(R.id.et_phone)
    private EditText et_phone;
    @ViewInject(R.id.et_houses)
    private EditText et_houses;
    @ViewInject(R.id.et_unit)
    private EditText et_unit;
    @ViewInject(R.id.et_housesnumber)
    private EditText et_housesnumber;


    @ViewInject(R.id.btn_find)
    private Button btn_find;//找回密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        context = this;
        ViewUtils.inject(this);
        dohanlder();
        Init();
    }


    private void Init() {
        //对找回密码按钮的监听
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInfo()) {
                    ForgetInfo();
                }
            }
        });


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
    }


    private void ForgetInfo() {
        RequestParams params = new RequestParams();
        params.put("mobile", et_phone.getText().toString());
        params.put("apartment", et_houses.getText().toString());
        params.put("unit", et_unit.getText().toString());
        params.put("door", et_housesnumber.getText().toString());
        HttpUtils.post(context, Url.pwdreset_validate, params, find_hanlder);
    }


    //检查输入信息是否合格,合格返回true,否则返回false
    private Boolean CheckInfo() {
        String phone = et_phone.getText().toString();
        String houses = et_houses.getText().toString();
        String unit = et_unit.getText().toString();
        String housesnumber = et_housesnumber.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(context, "手机号码不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.length() != 11) {
            Toast.makeText(context, "手机号码格式不对!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(houses)) {
            Toast.makeText(context, "楼盘名称不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(unit)) {
            Toast.makeText(context, "单元号不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(housesnumber)) {
            Toast.makeText(context, "门牌号不能为空!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void dohanlder() {
        find_hanlder = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                System.out.println(rst);
//                Toast.makeText(context,rst,Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    String result = jsonObject.getString("result");
                    if (result.equals("success")) {
//                        long id = jsonObject.getLong("id");//将ID传入重置密码界面使用
                        Intent intent = new Intent(ForgetPwdActivity.this,PwdUpdateActivity.class);//成功后跳转到重置密码界面
//                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
                    }else{
                        String message = jsonObject.getString("message");
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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


    //直接点击返回按钮
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
    }
}
