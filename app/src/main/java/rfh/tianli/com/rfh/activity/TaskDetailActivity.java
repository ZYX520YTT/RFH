package rfh.tianli.com.rfh.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.adapter.TaskDetailAdapter;
import rfh.tianli.com.rfh.domain.TaskDetailInfo;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;

public class TaskDetailActivity extends Activity implements EasyPermissions.PermissionCallbacks {

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    private Context context;

    private AsyncHttpResponseHandler taskdetail_handler, taskstart_handler, taskfinish_handler;
    /********
     * 返回
     *********/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;
    @ViewInject(R.id.ls_show)
    private RecyclerView ls_show;
    @ViewInject(R.id.btn_task)
    private Button btn_task;
    @ViewInject(R.id.btn_postremark)
    private Button btn_postremark;

    /**
     * 扫码
     **/
    @ViewInject(R.id.tv_sm)
    private TextView tv_sm;
    @ViewInject(R.id.iv_sm)
    private ImageView iv_sm;


    private List<TaskDetailInfo> taskDetailInfoList;
    private long taskId;
    private String status;
    private int position;

    private String needRemark;
    private String taskneedRemark;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        context = this;
        ViewUtils.inject(this);
        dohandler();
        Init();
    }

    private void Init() {

        taskDetailInfoList = new ArrayList<>();
        //对返回按钮的监听
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("position",position);
                intent.putExtra("status",status);
                setResult(Activity.RESULT_OK,intent);
                finish();
                overridePendingTransition(R.anim.out_right_in, R.anim.out_left_out);
            }
        });
        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("position",position);
                intent.putExtra("status",status);
                setResult(Activity.RESULT_OK,intent);
                finish();
                overridePendingTransition(R.anim.out_right_in, R.anim.out_left_out);
            }
        });


        final Intent intent = getIntent();
        taskId = intent.getLongExtra("taskId", 0);
        status = intent.getStringExtra("status");
        position=intent.getIntExtra("position",0);
        getTaskInfo(taskId);


        btn_postremark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, RemarkActivity.class);
                intent1.putExtra("taskId", taskId);
                startActivity(intent1);
            }
        });


        if (status.equals("1")) {
            btn_task.setText("开始任务");
        } else {
            btn_task.setText("完成任务");
        }

        ls_show.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        ls_show.setItemAnimator(null);


        tv_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals("3")) {
                    //跳到扫码界面
                    Intent intent1 = new Intent(TaskDetailActivity.this, ScanActivity.class);
                    intent1.putExtra("needRemark", needRemark);
                    intent1.putExtra("taskId", taskId);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.in_left_in, R.anim.in_right_out);
                } else if (status.equals("2")) {
                    //跳到扫码界面
                    Intent intent1 = new Intent(TaskDetailActivity.this, ScanActivity.class);
                    intent1.putExtra("needRemark", needRemark);
                    intent1.putExtra("taskId", taskId);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.in_left_in, R.anim.in_right_out);
                } else {
                    Toast.makeText(context, "任务为开始,请先开始任务!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        iv_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals("3")) {
                    Toast.makeText(context, "任务已完成", Toast.LENGTH_SHORT).show();
                } else if (status.equals("2")) {
                    //跳到扫码界面
                    Intent intent1 = new Intent(TaskDetailActivity.this, ScanActivity.class);
                    intent1.putExtra("needRemark", needRemark);
                    intent1.putExtra("taskId", taskId);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.in_left_in, R.anim.in_right_out);
                } else {
                    Toast.makeText(context, "任务为开始,请先开始任务!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //点击任务按钮
        btn_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status.equals("3")) {//调用完成任务的接口
                    RequestParams params = new RequestParams();
                    params.put("taskId", taskId);
                    HttpUtils.post(context, Url.task_finish, params, taskfinish_handler);
                } else {////调用开始任务的接口
                    RequestParams params = new RequestParams();
                    params.put("taskId", taskId);
                    HttpUtils.post(context, Url.task_start, params, taskstart_handler);
                }
            }
        });


    }

    private void getTaskInfo(long taskId) {
        if(taskDetailInfoList!=null){
            taskDetailInfoList.clear();
        }
        RequestParams params = new RequestParams();
        params.put("taskId", taskId);
        HttpUtils.get(context, Url.task_detail, params, taskdetail_handler);
    }


    private void dohandler() {
        taskdetail_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    /****任务详细信息**/
                    long taskid = 0;
                    String taskname = null;
                    String tasktype = null;
                    Integer tasktimeLimit = 0;
                    taskneedRemark = null;
                    String taskapartmentName = null;
                    String taskstatus = null;
                    long taskexecuteTime = 0;
                    String taskcreatorName = null;
                    String taskexecutorName = null;
                    long taskstartTime = 0;
                    long taskendTime = 0;
                    Integer tasktimeTaking = 0;
                    String taskremarks = null;


                    String result = jsonObject.getString("result");
                    if (result.equals("success")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("taskDetail");
                        try {
                            taskid = jsonObject1.getLong("id");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskname = jsonObject1.getString("name");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            tasktype = jsonObject1.getString("type");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            tasktimeLimit = jsonObject1.getInt("timeLimit");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskneedRemark = jsonObject1.getString("needRemark");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskapartmentName = jsonObject1.getString("apartmentName");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskstatus = jsonObject1.getString("status");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskexecuteTime = jsonObject1.getLong("executeTime");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskcreatorName = jsonObject1.getString("creatorName");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskexecutorName = jsonObject1.getString("executorName");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskstartTime = jsonObject1.getLong("startTime");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskendTime = jsonObject1.getLong("endTime");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            tasktimeTaking = jsonObject1.getInt("timeTaking");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            taskremarks = jsonObject1.getString("remarks");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if(taskneedRemark.equals("0")){
                            btn_postremark.setVisibility(View.GONE);
                        }else{
                            btn_postremark.setVisibility(View.VISIBLE);
                        }

                        JSONArray jsonArray = jsonObject.getJSONArray("deviceList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            /***需要巡检的设备列表****/
                            long id = 0;
                            String code = null;
                            String name = null;
                            String brand = null;
                            String model = null;
                            String location = null;
                            String deviceStatus = null;
                            String inheritData = null;
                            Integer serialNumber = 0;
                            String qrCodePath = null;
                            String typeName = null;
                            String apartmentName = null;
                            String systemName = null;
                            Integer sortNumber = 0;
                            String patrolStatus = null;

                            try {
                                id = jsonObject2.getLong("id");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                code = jsonObject2.getString("code");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                name = jsonObject2.getString("name");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                brand = jsonObject2.getString("brand");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                model = jsonObject2.getString("model");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                location = jsonObject2.getString("location");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                deviceStatus = jsonObject2.getString("deviceStatus");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                inheritData = jsonObject2.getString("inheritData");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                serialNumber = jsonObject2.getInt("serialNumber");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                qrCodePath = jsonObject2.getString("qrCodePath");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                typeName = jsonObject2.getString("typeName");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                apartmentName = jsonObject2.getString("apartmentName");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                systemName = jsonObject2.getString("systemName");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                sortNumber = jsonObject2.getInt("sortNumber");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                patrolStatus = jsonObject2.getString("patrolStatus");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            TaskDetailInfo taskDetailInfo = new TaskDetailInfo(taskid,
                                    taskstatus, taskcreatorName, name, patrolStatus,
                                    sortNumber, systemName, apartmentName, typeName,
                                    qrCodePath, serialNumber, inheritData, deviceStatus, location
                                    , model, brand, code, id, taskremarks, tasktimeTaking,
                                    taskendTime, taskstartTime, taskexecutorName, taskexecuteTime,
                                    taskapartmentName, tasktimeLimit, taskneedRemark, tasktype,
                                    taskname);
                            taskDetailInfoList.add(taskDetailInfo);
                        }

                        ls_show.setAdapter(new TaskDetailAdapter(context, taskDetailInfoList));


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
                Toast.makeText(context, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };


        taskstart_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    String result = jsonObject.getString("result");
                    String message = jsonObject.getString("message");
                    if (result.equals("success")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("taskInfo");
                        status = jsonObject1.getString("status");
                        needRemark = jsonObject1.getString("needRemark");
                        if (status.equals("1")) {
                            btn_task.setText("开始任务");
                        } else {
                            btn_task.setText("完成任务");
                        }
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };


        taskfinish_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    String result = jsonObject.getString("result");
                    String message = jsonObject.getString("message");
                    if (result.equals("success")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("taskInfo");
                        status = jsonObject1.getString("status");
                        if (status.equals("1")) {
                            btn_task.setText("开始任务");
                        } else {
                            btn_task.setText("完成任务");
                        }
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
            }
        };
    }


    //直接点击返回按钮
    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("position",position);
        intent.putExtra("status",status);
        setResult(Activity.RESULT_OK,intent);
        finish();
        overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
    }

    //重新加载数据


    @Override
    protected void onRestart() {
        super.onRestart();
        getTaskInfo(taskId);
    }

    @Override
    public void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(context, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }


}
