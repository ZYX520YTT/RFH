package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.domain.PersonInfo;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;

public class PersonInfoActivity extends Activity {

    private Context context;

    private AsyncHttpResponseHandler info_handler;

    /********
     * 返回
     *********/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;


    @ViewInject(R.id.rl_1)
    private RelativeLayout rl_1;
    @ViewInject(R.id.rl_2)
    private RelativeLayout rl_2;
    @ViewInject(R.id.rl_3)
    private RelativeLayout rl_3;
    @ViewInject(R.id.rl_4)
    private RelativeLayout rl_4;
    @ViewInject(R.id.rl_5)
    private RelativeLayout rl_5;
    @ViewInject(R.id.rl_6)
    private RelativeLayout rl_6;
    @ViewInject(R.id.rl_7)
    private RelativeLayout rl_7;
    @ViewInject(R.id.rl_8)
    private RelativeLayout rl_8;
    @ViewInject(R.id.rl_9)
    private RelativeLayout rl_9;
    @ViewInject(R.id.rl_10)
    private RelativeLayout rl_10;
    @ViewInject(R.id.rl_11)
    private RelativeLayout rl_11;


    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_pphone)
    private TextView tv_pphone;
    @ViewInject(R.id.tv_sex)
    private TextView tv_sex;
    @ViewInject(R.id.tv_apartname)
    private TextView tv_apartname;
    @ViewInject(R.id.tv_apartaddress)
    private TextView tv_apartaddress;
    @ViewInject(R.id.tv_unitname)
    private TextView tv_unitname;
    @ViewInject(R.id.tv_roomNumber)
    private TextView tv_roomNumber;
    @ViewInject(R.id.tv_ename)
    private TextView tv_ename;
    @ViewInject(R.id.tv_ephone)
    private TextView tv_ephone;
    @ViewInject(R.id.tv_organization)
    private TextView tv_organization;
    @ViewInject(R.id.tv_roleList)
    private TextView tv_roleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        context=this;
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


        RequestParams params=new RequestParams();
        HttpUtils.get(context, Url.sys_info,params,info_handler);
    }

    //设置信息
    private void SetInfo(PersonInfo personInfo){
        if(!TextUtils.isEmpty(personInfo.getProprietorInforealName())){
            rl_1.setVisibility(View.VISIBLE);
            tv_name.setText(personInfo.getProprietorInforealName());
        }else{
            rl_1.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getProprietorInfomobile())){
            rl_2.setVisibility(View.VISIBLE);
            tv_pphone.setText(personInfo.getProprietorInfomobile());
        }else{
            rl_2.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getProprietorInfosex())){
            rl_3.setVisibility(View.VISIBLE);
            if((personInfo.getProprietorInfosex()).equals("1")){
                tv_sex.setText("男");
            }else{
                tv_sex.setText("女");
            }
        }else{
            rl_3.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getApartmentname())){
            rl_4.setVisibility(View.VISIBLE);
            tv_apartname.setText(personInfo.getApartmentname());
        }else{
            rl_4.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getApartmentaddress())){
            rl_5.setVisibility(View.VISIBLE);
            tv_apartaddress.setText(personInfo.getApartmentaddress());
        }else{
            rl_5.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getUnitname())){
            rl_6.setVisibility(View.VISIBLE);
            tv_unitname.setText(personInfo.getUnitname());
        }else{
            rl_6.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getRoomNumber())){
            rl_7.setVisibility(View.VISIBLE);
            tv_roomNumber.setText(personInfo.getRoomNumber());
        }else{
            rl_7.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getEmployeeInforealName())){
            rl_8.setVisibility(View.VISIBLE);
            tv_ename.setText(personInfo.getEmployeeInforealName());
        }else{
            rl_8.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getEmployeeInfomobile())){
            rl_9.setVisibility(View.VISIBLE);
            tv_ephone.setText(personInfo.getEmployeeInfomobile());
        }else{
            rl_9.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getOrganizationname())){
            rl_10.setVisibility(View.VISIBLE);
            tv_organization.setText(personInfo.getOrganizationname());
        }else{
            rl_10.setVisibility(View.GONE);
        }

        if(!TextUtils.isEmpty(personInfo.getRoleListname())){
            rl_11.setVisibility(View.VISIBLE);
            tv_roleList.setText(personInfo.getRoleListname());
        }else{
            rl_11.setVisibility(View.GONE);
        }
    }

    private void dohandler() {
        info_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String result=jsonObject.getString("result");
                    if(result.equals("success")){
                        String proprietorInforealName=null;
                        String proprietorInfomobile=null;
                        String proprietorInfosex=null;
                        String apartmentname=null;
                        String apartmentaddress=null;
                        String unitname=null;
                        String roomNumber=null;
                        String employeeInfomobile=null;
                        String employeeInforealName=null;
                        String organizationname=null;
                        String roleListname=null;



                        try {
                            JSONObject jsonObject1=jsonObject.getJSONObject("proprietorInfo");
                            proprietorInforealName=jsonObject1.getString("realName");
                            proprietorInfomobile=jsonObject1.getString("mobile");
                            proprietorInfosex=jsonObject1.getString("sex");
                            JSONObject jsonObject2=jsonObject1.getJSONObject("apartment");
                            apartmentname=jsonObject2.getString("name");
                            apartmentaddress=jsonObject2.getString("address");
                            JSONObject jsonObject3=jsonObject1.getJSONObject("unit");
                            unitname=jsonObject3.getString("name");
                            JSONObject jsonObject4=jsonObject1.getJSONObject("door");
                            roomNumber=jsonObject4.getString("roomNumber");
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        try {
                            JSONObject jsonObject5=jsonObject.getJSONObject("employeeInfo");
                            employeeInfomobile=jsonObject5.getString("mobile");
                            employeeInforealName=jsonObject5.getString("realName");
                            JSONObject jsonObject6=jsonObject5.getJSONObject("organization");
                            organizationname=jsonObject6.getString("name");
                            JSONArray jsonArray=jsonObject5.getJSONArray("roleList");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject7=jsonArray.getJSONObject(i);
                                roleListname=jsonObject7.getString("name");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        PersonInfo personInfo=new PersonInfo(proprietorInfomobile,roleListname,
                                organizationname,employeeInforealName,employeeInfomobile,
                                roomNumber,unitname,apartmentaddress,apartmentname,proprietorInfosex
                        ,proprietorInforealName);

                        SetInfo(personInfo);


                    }else{
                        String message=jsonObject.getString("message");
                        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
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
