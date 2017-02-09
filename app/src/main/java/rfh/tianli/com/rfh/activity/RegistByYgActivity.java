package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import rfh.tianli.com.rfh.adapter.GroupAdapter;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;

public class RegistByYgActivity extends Activity {

    private Context context;


    private AsyncHttpResponseHandler sys_org_handler,sys_role_handler;


    private List<String> sys_org;//部门信息
    private List<String> sys_role;//权限信息
    /********
     * 返回
     *********/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;


    @ViewInject(R.id.et_phonenumber)//电话号码
    private EditText et_phonenumber;
    @ViewInject(R.id.et_name)//姓名
    private EditText et_name;
    @ViewInject(R.id.et_pwd1)//密码
    private EditText et_pwd1;
    @ViewInject(R.id.et_pwd2)//确认密码
    private EditText et_pwd2;

    @ViewInject(R.id.tv_apart)
    private TextView tv_apart;
    @ViewInject(R.id.tv_qx)
    private TextView tv_qx;

    @ViewInject(R.id.iv_apart)
    private ImageView iv_apart;
    @ViewInject(R.id.iv_qx)
    private ImageView iv_qx;
    private GroupAdapter org_adapter;
    private GroupAdapter role_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_by_yg);
        context = this;
        ViewUtils.inject(this);
        dohanlder();
        Init();
    }


    private void Init(){
        sys_org=new ArrayList<>();
        sys_role=new ArrayList<>();
        //一开始就请求网络获取部门信息和权限信息
        RequestParams org_params=new RequestParams();
        HttpUtils.get(context, Url.sys_org,org_params,sys_org_handler);

        RequestParams role_params=new RequestParams();
        HttpUtils.get(context,Url.sys_role,role_params,sys_role_handler);



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


        iv_apart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGroupList(iv_apart,org_adapter,true);
            }
        });


        iv_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGroupList(iv_qx,role_adapter,false);
            }
        });


        org_adapter = new GroupAdapter(context,sys_org,0);
        role_adapter = new GroupAdapter(context,sys_role,0);
    }



    private PopupWindow popupWindow;

    //展示列表部门和权限的工具
    private void showGroupList(final View anchor, final GroupAdapter adapter, final Boolean isapart){
        int size=getResources().getDimensionPixelOffset(R.dimen.regist_group);
        if(popupWindow==null&&adapter!=null){
            View layout=View.inflate(context,R.layout.regist_group,null);
            popupWindow = new PopupWindow(layout, size, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            ListView lv_list = (ListView) layout.findViewById(R.id.lv_list);
            lv_list.setAdapter(adapter);
            lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapter.setIndex(position);
                    if(isapart){
                        tv_apart.setText(sys_org.get(position));
                    }else{
                        tv_qx.setText(sys_role.get(position));
                    }
                }
            });
        }
        popupWindow.showAsDropDown(anchor, (anchor.getWidth() - size) / 2, 0);
    }


    private void dohanlder(){
        sys_org_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String result=jsonObject.getString("result");
                    System.out.println("结果1："+result);
                    if(result.equals("success")){
                        JSONArray jsonArray=jsonObject.getJSONArray("orgList");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            sys_org.add(jsonObject1.getString("name"));
                        }
                    }else{
                        Toast.makeText(context,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
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


        sys_role_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String result=jsonObject.getString("result");
                    System.out.println("结果2："+result);
                    if(result.equals("success")){
                        JSONArray jsonArray=jsonObject.getJSONArray("roleList");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            sys_role.add(jsonObject1.getString("name"));
                        }
                    }else{
                        Toast.makeText(context,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
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
