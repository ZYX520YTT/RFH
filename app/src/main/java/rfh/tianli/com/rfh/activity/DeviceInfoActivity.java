package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.adapter.DeviceInfoAdapter;
import rfh.tianli.com.rfh.domain.DeviceDataInfo;
import rfh.tianli.com.rfh.domain.DeviceInfo;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;
import rfh.tianli.com.rfh.widget.ShowListView;

public class DeviceInfoActivity extends Activity {

    private Context context;
    private AsyncHttpResponseHandler deviceinfo_handler;

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


    @ViewInject(R.id.tv_author)
    private TextView tv_author;
    @ViewInject(R.id.tv_project)
    private TextView tv_project;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_code)
    private TextView tv_code;
    @ViewInject(R.id.tv_brand)
    private TextView tv_brand;
    @ViewInject(R.id.tv_location)
    private TextView tv_location;
    @ViewInject(R.id.tv_model)
    private TextView tv_model;
    @ViewInject(R.id.ls_show)
    private ShowListView ls_show;

    private String author;
    private String project;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
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

        Intent intent = getIntent();
        author = intent.getStringExtra("author");
        project = intent.getStringExtra("project");
        code = intent.getStringExtra("code");


        deviceinfo();
    }

    private void deviceinfo() {
        RequestParams params = new RequestParams();
        params.put("code", code);
        HttpUtils.get(context, Url.device_info, params, deviceinfo_handler);
    }


    private void SetDeviceInfo(DeviceInfo deviceInfo) {
        if (!TextUtils.isEmpty(deviceInfo.getAuthor())) {
            tv_author.setText(deviceInfo.getAuthor());
        } else {
            rl_1.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(deviceInfo.getProject())) {
            tv_project.setText(deviceInfo.getProject());
        } else {
            rl_2.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(deviceInfo.getName())) {
            tv_name.setText(deviceInfo.getName());
        } else {
            rl_3.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(deviceInfo.getCode())) {
            tv_code.setText(deviceInfo.getCode());
        } else {
            rl_4.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(deviceInfo.getBrand())) {
            tv_brand.setText(deviceInfo.getBrand());
        } else {
            rl_5.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(deviceInfo.getLocation())) {
            tv_location.setText(deviceInfo.getLocation());
        } else {
            rl_6.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(deviceInfo.getModel())) {
            tv_model.setText(deviceInfo.getModel());
        } else {
            rl_7.setVisibility(View.GONE);
        }

    }


    private void dohandler() {
        deviceinfo_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    String result = jsonObject.getString("result");

                    if (result.equals("success")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("device");
                        String brand = jsonObject1.getString("brand");
                        String location = jsonObject1.getString("location");
                        String model = jsonObject1.getString("model");
                        String name1 = jsonObject1.getString("name");
                        DeviceInfo deviceInfo = new DeviceInfo(brand, project,
                                author, name1, model, location, code);
                        SetDeviceInfo(deviceInfo);

                        List<DeviceDataInfo> deviceDataInfoList = new ArrayList<>();
                        JSONArray jsonArray = jsonObject1.getJSONArray("dataList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            String code = jsonObject2.getString("code");
                            String dataType = jsonObject2.getString("dataType");
                            String description = jsonObject2.getString("description");
                            long id = jsonObject2.getLong("id");
                            String name = jsonObject2.getString("name");
                            String sortNumber = jsonObject2.getString("sortNumber");
                            String unit = jsonObject2.getString("unit");
                            String widgetType = jsonObject2.getString("widgetType");
                            DeviceDataInfo deviceDataInfo = new DeviceDataInfo(code, widgetType
                                    , unit, sortNumber, name, id, description, dataType);
                            deviceDataInfoList.add(deviceDataInfo);
                        }

                        /********************设置信息*****************************/

                        ls_show.setAdapter(new DeviceInfoAdapter(context,deviceDataInfoList));


                        /********************设置信息*****************************/


                    } else {
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
        if(DeviceInfoAdapter.pvTime1.isShowing()){
            DeviceInfoAdapter.pvTime1.dismiss();
            return;
        }
        if(DeviceInfoAdapter.pvTime2.isShowing()){
            DeviceInfoAdapter.pvTime2.dismiss();
            return;
        }
        finish();
        overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
    }


}
