package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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
import rfh.tianli.com.rfh.adapter.OrgGroupAdapter;
import rfh.tianli.com.rfh.adapter.RoleGroupAdapter;
import rfh.tianli.com.rfh.domain.OrgListInfo;
import rfh.tianli.com.rfh.domain.RoleListInfo;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;

public class RegistByYgActivity extends Activity {

    private Context context;


    private AsyncHttpResponseHandler sys_org_handler, sys_role_handler,regist_handler;


    private List<OrgListInfo> sys_org;//部门信息
    private List<RoleListInfo> sys_role;//权限信息

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
    @ViewInject(R.id.cb_agree)//同意并接受注册协议
    private CheckBox cb_agree;
    @ViewInject(R.id.bt_finish)//完成注册
    private Button bt_finish;



    private OrgGroupAdapter org_adapter;
    private RoleGroupAdapter role_adapter;


    private long organizationId;//选择的部门ID
    private long roleId;//选择的权限的ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_by_yg);
        context = this;
        ViewUtils.inject(this);
        dohanlder();
        Init();
    }


    private void Init() {
        sys_org = new ArrayList<>();
        sys_role = new ArrayList<>();
        //一开始就请求网络获取部门信息和权限信息
        RequestParams org_params = new RequestParams();
        HttpUtils.get(context, Url.sys_org, org_params, sys_org_handler);

        RequestParams role_params = new RequestParams();
        HttpUtils.get(context, Url.sys_role, role_params, sys_role_handler);


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
                showGroupList_apart(iv_apart, org_adapter);
            }
        });


        iv_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGroupList_qx(iv_qx, role_adapter);
            }
        });

        //点击"完成注册按钮"
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInfo()){
                    RegistInfo();
                }
            }
        });


        org_adapter = new OrgGroupAdapter(context, sys_org, 0);

        role_adapter = new RoleGroupAdapter(context, sys_role, 0);
    }

    private void RegistInfo(){
        RequestParams params=new RequestParams();
        params.put("mobile",et_phonenumber.getText().toString());
        params.put("password",et_pwd1.getText().toString());
        params.put("realName",et_name.getText().toString());
        params.put("organizationId",organizationId);
        params.put("roleId",roleId);
        HttpUtils.post(context,Url.employee_register,params,regist_handler);

    }


    //检查注册信息
    private Boolean CheckInfo(){
        String phone=et_phonenumber.getText().toString();
        String name=et_name.getText().toString();
        String pwd1=et_pwd1.getText().toString();
        String pwd2=et_pwd2.getText().toString();
        String apart=tv_apart.getText().toString();
        String qx=tv_qx.getText().toString();
        boolean agree=cb_agree.isChecked();
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(context,"电话号码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phone.length()!=11){
            Toast.makeText(context,"电话号码格式不正确!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(name)){
            Toast.makeText(context,"姓名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pwd1)){
            Toast.makeText(context,"密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pwd1.length()<6||pwd1.length()>16){
            Toast.makeText(context,"密码在6~16位数字之间!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pwd2)){
            Toast.makeText(context,"确认密码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pwd2.length()<6||pwd2.length()>16){
            Toast.makeText(context,"密码在6~16位数字之间!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pwd1.equals(pwd2)){
            Toast.makeText(context,"两次输入的密码不同!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(apart)){
            Toast.makeText(context,"部门不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(qx)){
            Toast.makeText(context,"权限申请不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!agree){
            Toast.makeText(context,"未同意并接受协议!",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private PopupWindow popupWindow_apart, popupWindow_qx;

    //展示列表部门的工具
    private void showGroupList_apart(final View anchor, final OrgGroupAdapter adapter) {
        int size = getResources().getDimensionPixelOffset(R.dimen.regist_group);
        if (popupWindow_apart == null && adapter != null) {
            View layout = View.inflate(context, R.layout.regist_group, null);
            popupWindow_apart = new PopupWindow(layout, size, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            popupWindow_apart.setBackgroundDrawable(new BitmapDrawable());
            ListView lv_list = (ListView) layout.findViewById(R.id.lv_list);
            lv_list.setAdapter(adapter);
            lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapter.setIndex(position);
                    tv_apart.setText(sys_org.get(position).getName());
                    organizationId=sys_org.get(position).getId();
                    popupWindow_apart.dismiss();
                }
            });
        }
        popupWindow_apart.showAsDropDown(anchor, (anchor.getWidth() - size) / 2, 0);
    }


    //展示列表权限的工具
    private void showGroupList_qx(final View anchor, final RoleGroupAdapter adapter) {
        int size = getResources().getDimensionPixelOffset(R.dimen.regist_group);
        if (popupWindow_qx == null && adapter != null) {
            View layout = View.inflate(context, R.layout.regist_group, null);
            popupWindow_qx = new PopupWindow(layout, size, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            popupWindow_qx.setBackgroundDrawable(new BitmapDrawable());
            ListView lv_list = (ListView) layout.findViewById(R.id.lv_list);
            lv_list.setAdapter(adapter);
            lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapter.setIndex(position);
                    tv_qx.setText(sys_role.get(position).getName());
                    roleId=sys_role.get(position).getId();
                    popupWindow_qx.dismiss();
                }
            });
        }
        popupWindow_qx.showAsDropDown(anchor, (anchor.getWidth() - size) / 2, 0);
    }






    private void dohanlder() {
        sys_org_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    String result = jsonObject.getString("result");
                    System.out.println("结果1：" + result);
                    if (result.equals("success")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("orgList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            long id=jsonObject1.getLong("id");
                            String name=jsonObject1.getString("name");
                            String type=jsonObject1.getString("type");
                            OrgListInfo orgListInfo=new OrgListInfo(id,name,type);
                            sys_org.add(orgListInfo);
                        }
                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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


        sys_role_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    String result = jsonObject.getString("result");
                    System.out.println("结果2：" + result);
                    if (result.equals("success")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("roleList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            long id=jsonObject1.getLong("id");
                            String name=jsonObject1.getString("name");
                            String code=jsonObject1.getString("code");
                            String description=jsonObject1.getString("description");
                            RoleListInfo roleListInfo=new RoleListInfo(id,description,code,name);
                            sys_role.add(roleListInfo);
                        }
                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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


        regist_handler=new AsyncHttpResponseHandler() {
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
