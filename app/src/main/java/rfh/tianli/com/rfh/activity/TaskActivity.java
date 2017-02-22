package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
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
import rfh.tianli.com.rfh.widget.pulltorefresh.PullToRefreshLayout;

public class TaskActivity extends Activity {

    public static Context context;
    private AsyncHttpResponseHandler undotask_handler;

    private int pageNum;
    private int pageSize=8;

    /********
     * 返回
     *********/
    @ViewInject(R.id.iv_return)
    private ImageView iv_return;
    @ViewInject(R.id.tv_return)
    private TextView tv_return;
    @ViewInject(R.id.ls_show)
    private ListView ls_show;

    @ViewInject(R.id.refresh_view)
    private PullToRefreshLayout refresh_view;

    private boolean isLoadmore;//true为加载更多，false为下拉刷新

    List<TaskInfo> taskInfoList;
    private TaskAdapter taskAdapter;


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




        taskInfoList=new ArrayList<>();
        refresh_view.setOnRefreshListener(new MyListener());
        getFirst();

    }




    //下拉刷新
    private void getFirst(){
        isLoadmore=false;
        pageNum=0;

        RequestParams params = new RequestParams();
        params.put("pageNum",pageNum);
        params.put("pageSize",pageSize);
        HttpUtils.get(context, Url.task_undo, params, undotask_handler);

        taskAdapter = new TaskAdapter(context,taskInfoList);
        ls_show.setAdapter(taskAdapter);

    }


    //下拉加载更多
    private void getNext(){
        isLoadmore=true;
        RequestParams params = new RequestParams();
        params.put("pageNum",pageNum);
        params.put("pageSize",pageSize);
        HttpUtils.get(context, Url.task_undo, params, undotask_handler);
    }

    private void dohandler() {
        undotask_handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(rst);
//                    System.out.println(rst);
                    String result = jsonObject.getString("result");
                    if (result.equals("success")) {
                        if(!isLoadmore){
                            taskInfoList.clear();
                        }
                        JSONArray jsonArray = jsonObject.getJSONArray("taskList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            long id=0;
                            String name=null;
                            String type=null;
                            Integer timeLimit=0;
                            String apartmentName=null;
                            String status=null;
                            long executeTime=0;
                            String creatorName=null;
                            String executorName=null;
                            long startTime=0;
                            long endTime=0;
                            String remarks=null;
                            Integer timeTaking=0;
                            String needRemark=null;
                            try {
                                id = jsonObject1.getLong("id");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                name = jsonObject1.getString("name");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                type = jsonObject1.getString("type");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                timeLimit = jsonObject1.getInt("timeLimit");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                apartmentName = jsonObject1.getString("apartmentName");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                status = jsonObject1.getString("status");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                executeTime = jsonObject1.getLong("executeTime");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                creatorName = jsonObject1.getString("creatorName");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                executorName = jsonObject1.getString("executorName");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                startTime=jsonObject1.getLong("startTime");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                endTime=jsonObject1.getLong("endTime");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                remarks=jsonObject1.getString("remarks");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                timeTaking=jsonObject1.getInt("timeTaking");
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            try {
                                needRemark=jsonObject1.getString("needRemark");
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            TaskInfo taskInfo=new TaskInfo(apartmentName,timeTaking,remarks,
                                    endTime,startTime,needRemark,type,status,name,
                                    timeLimit,id,executorName,executeTime,creatorName);
                            taskInfoList.add(taskInfo);
                        }

                        /******设置数据************/
//                        ls_show.setAdapter(new TaskAdapter(context, taskInfoList));

                         taskAdapter.notifyDataSetChanged();
                        /******设置数据************/
                        pageNum+=pageSize;
                        if(isLoadmore){
                            refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }else{
                            refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
                        }
                    } else {
                        String message = jsonObject.getString("message");
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        if(isLoadmore){
                            refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                        }else{
                            refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(isLoadmore){
                        refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                    }else{
                        refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                String rst=new String(responseBody);
//                System.out.println("解析结果:"+rst);
                Toast.makeText(context, R.string.toast_network_error1, Toast.LENGTH_SHORT).show();
                if(isLoadmore){
                    refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
                }else{
                    refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                }
            }
        };





    }

    //直接点击返回按钮
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.out_up_in, R.anim.out_down_out);
    }



    class MyListener implements PullToRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            getFirst();
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            getNext();
        }
    }
}
