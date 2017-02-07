package rfh.tianli.com.rfh.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;

public class RegistActivity extends BaseActivity {

    private AsyncHttpResponseHandler regist_hanlder,allhouse_handler;
    private Context context;

    private List<String> hename;
    private List<Long> henumber;
    private Long ID;

    /****点击返回*****/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;
    /****点击返回*****/


    /******输入的内容*******/
    @ViewInject(R.id.et_phonenumber)//电话号码
    private EditText et_phonenumber;
    @ViewInject(R.id.et_name)//姓名
    private EditText et_name;
    @ViewInject(R.id.et_houses)//楼盘名称
    private EditText et_houses;
    @ViewInject(R.id.et_housesnumber)//门牌号码
    private EditText et_housesnumber;
    @ViewInject(R.id.et_setpwd)//设置密码
    private EditText et_setpwd;
    @ViewInject(R.id.et_rgpwd)//确认密码
    private EditText et_rgpwd;
    /******输入的内容*******/

    /******同意并注册*******/
    @ViewInject(R.id.cb_agree)
    private CheckBox cb_agree;
    /******同意并注册*******/

    /**********完成注册***********/
    @ViewInject(R.id.bt_finish)
    private Button bt_finish;
    /**********完成注册***********/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        context=this;
        ViewUtils.inject(this);
        dohandler();
        Init();
    }
    private void Init(){
        hename=new ArrayList<>();
        henumber=new ArrayList<>();

        RequestParams params=new RequestParams();
        HttpUtils.get(context,Url.apartment,params,allhouse_handler);

        /*************测试数据*********************/
//        et_phonenumber.setText("13408661976");
//        et_name.setText("wang");
//        et_houses.setText("12");
//        et_housesnumber.setText("12");
//        et_setpwd.setText("zyx123");
//        et_rgpwd.setText("zyx123");
//        cb_agree.setChecked(true);
        /**************测试数据********************/

        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.out_right_in,R.anim.out_left_out);
            }
        });
        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.out_right_in,R.anim.out_left_out);
            }
        });

        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInfo()){
//                    bt_finish.setEnabled(false);//点了登录后不可以再点，避免用户乱点
                    RegistInfo();
                }
            }
        });




    }


    private void RegistInfo(){
        RequestParams params=new RequestParams();

        params.put("mobile",et_phonenumber.getText().toString());
        params.put("password",et_setpwd.getText().toString());
        params.put("apartmentId",ID);
        params.put("roomNumber",et_housesnumber.getText().toString());
        HttpUtils.post(context, Url.register,params,regist_hanlder);
    }




    //检查注册信息是否合格，合格返回true,否则false
    private Boolean CheckInfo(){
        String phonenumber=et_phonenumber.getText().toString();
        String name=et_name.getText().toString();
        String houses=et_houses.getText().toString();
        String housesnumber=et_housesnumber.getText().toString();
        String setpwd=et_setpwd.getText().toString();
        String rgpwd=et_rgpwd.getText().toString();
        boolean ischeck=cb_agree.isChecked();
        if(phonenumber.length()!=11){
            Toast.makeText(context,"电话号码格式错误!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(name)){
            Toast.makeText(context,"姓名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(houses)){
            Toast.makeText(context,"楼盘名称不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean ok=false;
        for(int i=0;i<hename.size();i++){
            String hname=hename.get(i);
            if(hname.equals(houses)){
                ok=true;
                ID=henumber.get(i);
                break;
            }
        }
        if(!ok){
            Toast.makeText(context,"楼盘名称错误!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(housesnumber)){
            Toast.makeText(context,"门牌号码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(setpwd.length()<6||setpwd.length()>16){
            Toast.makeText(context,"密码格式不正确!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!rgpwd.equals(setpwd)){
            Toast.makeText(context,"两次输入的密码不同!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!ischeck){
            Toast.makeText(context,"未同意协议!",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            finish();
            overridePendingTransition(R.anim.out_right_in,R.anim.out_left_out);
        }
    };


    //直接点击返回按钮
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.out_up_in,R.anim.out_down_out);
    }


    private void dohandler(){
        regist_hanlder=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                System.out.println(rst);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String result=jsonObject.getString("result");
                    if(result.equals("error")){
                        Toast.makeText(context,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,"注册成功!",Toast.LENGTH_SHORT).show();
                        handler.sendEmptyMessageDelayed(0,500);
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


        allhouse_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                System.out.println(rst);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String result=jsonObject.getString("result");

                    if(result.equals("success")){
                        JSONArray apartments=jsonObject.getJSONArray("apartments");
                        for(int i=0;i<apartments.length();i++){
                            JSONObject jsonObject1=apartments.getJSONObject(i);
                            String name=jsonObject1.getString("name");
                            long id=jsonObject1.getLong("id");
                            hename.add(name);
                            henumber.add(id);
                        }
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
