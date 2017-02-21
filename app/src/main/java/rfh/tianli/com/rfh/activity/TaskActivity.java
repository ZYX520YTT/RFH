package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import rfh.tianli.com.rfh.adapter.TaskAdapter;
import rfh.tianli.com.rfh.domain.TaskInfo;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;

public class TaskActivity extends Activity {

    private Context context;
    private AsyncHttpResponseHandler undotask_handler;

    /********
     * 返回
     *********/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;
    @ViewInject(R.id.ls_show)
    private RecyclerView ls_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
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


        getUndoTask();

    }

    private void getUndoTask() {
        RequestParams params = new RequestParams();
        HttpUtils.get(context, Url.task_undo, params, undotask_handler);
    }

    private void dohandler() {
        undotask_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
                    System.out.println(rst);
                    String result = jsonObject.getString("result");
                    if (result.equals("success")) {
                        List<TaskInfo> taskInfoList = new ArrayList<>();
                        JSONArray jsonArray = jsonObject.getJSONArray("taskList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            long id = jsonObject1.getLong("id");
                            String name = jsonObject1.getString("name");
                            String type = jsonObject1.getString("type");
                            Integer timeLimit = jsonObject1.getInt("timeLimit");
                            String apartmentName = jsonObject1.getString("apartmentName");
                            String status = jsonObject1.getString("status");
                            long executeTime = jsonObject1.getLong("executeTime");
                            String creatorName = jsonObject1.getString("creatorName");
                            String executorName = jsonObject1.getString("executorName");
                            TaskInfo taskInfo = new TaskInfo(apartmentName, type, status
                                    , name, timeLimit, id, executorName, executeTime, creatorName);
                            taskInfoList.add(taskInfo);
                        }

                        /******设置数据************/
                        ls_show.setAdapter(new TaskAdapter(context, taskInfoList));

                        /******设置数据************/
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

    }

    //直接点击返回按钮
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
    }
}
