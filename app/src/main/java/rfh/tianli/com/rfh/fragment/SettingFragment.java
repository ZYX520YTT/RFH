package rfh.tianli.com.rfh.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.activity.LoginActivity;
import rfh.tianli.com.rfh.activity.PersonInfoActivity;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;
import rfh.tianli.com.rfh.thread.User;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 16:48.
 * 描述：个人设置页面
 */

public class SettingFragment extends Fragment {

    private View view;
    private Context context;

    @ViewInject(R.id.rlyt_info)
    private RelativeLayout rlyt_info;


    private AsyncHttpResponseHandler loginout_handler;


    /***注销****/
    @ViewInject(R.id.tv_out)
    private TextView tv_out;
    @ViewInject(R.id.iv_out)
    private ImageView iv_out;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting,container,false);
        ViewUtils.inject(this,view);
        dohandler();
        Init();
        return view;
    }

    private void Init(){
        rlyt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, PersonInfoActivity.class));
                ((Activity) MainActivity.context).overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
            }
        });


        //监听用户点击"注销按钮"
        tv_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginout();
            }
        });
        iv_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginout();
            }
        });
    }

    private void loginout(){
        RequestParams params=new RequestParams();
        HttpUtils.post(context, Url.logout,params,loginout_handler);
    }

    private void dohandler(){
        loginout_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String result=jsonObject.getString("result");
                    if(result.equals("success")){
                        startActivity(new Intent(context, LoginActivity.class));
                        getActivity().finish();
                        ((Activity) MainActivity.context).overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
                        User user=new User(getActivity());
                        user.removePass();
                        user.removeUserNumber();
                        user.removeUserPower();
                    }
                    String message=jsonObject.getString("message");
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
