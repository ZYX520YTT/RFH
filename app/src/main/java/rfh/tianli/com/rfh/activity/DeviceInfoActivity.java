package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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
import rfh.tianli.com.rfh.domain.PatrolInfo;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;
import rfh.tianli.com.rfh.widget.ShowListView;

public class DeviceInfoActivity extends Activity {

    private Context context;
    private AsyncHttpResponseHandler deviceinfo_handler, saveValues_handler;

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
    @ViewInject(R.id.btn_post)
    private Button btn_post;

    private String author;
    private String project;
    private String code;
    private long taskId;
    private String deviceId;

    List<DeviceDataInfo> deviceDataInfoList;
    private DeviceInfoAdapter adapter;

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
        deviceDataInfoList = new ArrayList<>();
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

        //提交数据
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PatrolInfo> patrolInfos = adapter.getData();
                String url = Url.saveValues + String.format("?taskId=%s&deviceId=%s", taskId, deviceId);
                HttpUtils.postJson(context, url, new Gson().toJson(patrolInfos), saveValues_handler);
            }
        });

        Intent intent = getIntent();
        author = intent.getStringExtra("author");
        project = intent.getStringExtra("project");
        code = intent.getStringExtra("code");
        taskId = intent.getLongExtra("taskId", 0);

        deviceinfo();

        adapter = new DeviceInfoAdapter(context, deviceDataInfoList);
        ls_show.setAdapter(adapter);
    }

    private void deviceinfo() {
        RequestParams params = new RequestParams();
        params.put("taskId", taskId);
        params.put("deviceCode", code);
//        params.put("deviceCode","FLTY-DT-JF-001");
        HttpUtils.get(context, Url.patrol_qr, params, deviceinfo_handler);
    }


    private void SetDeviceInfo(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            rl_3.setVisibility(View.GONE);
            rl_4.setVisibility(View.GONE);
            return;
        }
        if (!TextUtils.isEmpty(deviceInfo.getDeviceName())) {
            tv_name.setText(deviceInfo.getDeviceName());
        } else {
            rl_3.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(deviceInfo.getDeviceCode())) {
            tv_code.setText(deviceInfo.getDeviceCode());
        } else {
            rl_4.setVisibility(View.GONE);
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
                        JSONArray jsonArray = jsonObject.getJSONArray("dataList");
                        String deviceName = jsonObject.getString("deviceName");
                        String deviceCode = jsonObject.getString("deviceCode");
                        deviceId = jsonObject.getString("deviceId");
                        DeviceInfo deviceInfo = new DeviceInfo(deviceName, deviceCode);
                        SetDeviceInfo(deviceInfo);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String code = null;
                            String dataType = null;
                            String description = null;
                            long id = 0;
                            String name = null;
                            Integer sortNumber = null;
                            String unit = null;
                            String widgetType = null;
                            String status = null;
                            long recordTime = 0;
                            String value = null;

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            try {
                                code = jsonObject1.getString("code");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                dataType = jsonObject1.getString("dataType");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                description = jsonObject1.getString("description");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                name = jsonObject1.getString("name");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                unit = jsonObject1.getString("unit");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                widgetType = jsonObject1.getString("widgetType");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                status = jsonObject1.getString("status");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                value = jsonObject1.getString("value");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                id = jsonObject1.getLong("id");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                recordTime = jsonObject1.getLong("recordTime");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                sortNumber = jsonObject1.getInt("sortNumber");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            DeviceDataInfo deviceDataInfo = new DeviceDataInfo(code, value,
                                    recordTime, status, widgetType, unit, sortNumber,
                                    name, id, description, dataType);
                            deviceDataInfoList.add(deviceDataInfo);
                        }
                        adapter.notifyDataSetChanged();


                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        SetDeviceInfo(null);
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


        saveValues_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
//                System.out.println("最终："+rst);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    String result = jsonObject.getString("result");
                    String message = jsonObject.getString("message");
                    if (result.equals("success")) {
                        Boolean isDeviceFinished = jsonObject.getBoolean("isDeviceFinished");

                        finish();
                        overridePendingTransition(R.anim.out_right_in, R.anim.out_left_out);
                    }
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


    //直接点击返回按钮
    @Override
    public void onBackPressed() {
        if (DeviceInfoAdapter.pvTime1 == null) {
            finish();
            overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
            return;
        }
        if (DeviceInfoAdapter.pvTime2 == null) {
            finish();
            overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
            return;
        }
        if (DeviceInfoAdapter.pvTime1.isShowing()) {
            DeviceInfoAdapter.pvTime1.dismiss();
            return;
        }
        if (DeviceInfoAdapter.pvTime2.isShowing()) {
            DeviceInfoAdapter.pvTime2.dismiss();
            return;
        }
        finish();
        overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
    }


}
