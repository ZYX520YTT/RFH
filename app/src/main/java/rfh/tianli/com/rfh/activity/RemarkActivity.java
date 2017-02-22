package rfh.tianli.com.rfh.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.thread.HttpUtils;
import rfh.tianli.com.rfh.thread.Url;

public class RemarkActivity extends Activity {

    private Context context;
    private AsyncHttpResponseHandler taskremark_handler;

    @ViewInject(R.id.btn_cancel)
    private Button btn_cancel;
    @ViewInject(R.id.btn_confrim)
    private Button btn_confrim;
    @ViewInject(R.id.et_message)
    private EditText et_message;

    private long taskId;
    private String remarks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);
        context=this;
        ViewUtils.inject(this);
        dohandler();
        Init();
    }
    private void Init(){
        Intent intent=getIntent();
        taskId=intent.getLongExtra("taskId",0);
        remarks=et_message.getText().toString();



        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(remarks)){
                    RequestParams params=new RequestParams();
                    params.put("taskId",taskId);
                    params.put("remarks",remarks);
                    HttpUtils.post(context, Url.task_remark,params,taskremark_handler);
                }else{
                    RequestParams params=new RequestParams();
                    params.put("taskId",taskId);
                    HttpUtils.post(context, Url.task_remark,params,taskremark_handler);
                }
            }
        });
    }


    private void dohandler(){
        taskremark_handler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String rst=new String(responseBody);
                try {
                    JSONObject jsonObject=new JSONObject(rst);
                    String message=jsonObject.getString("message");
                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
                    finish();
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
