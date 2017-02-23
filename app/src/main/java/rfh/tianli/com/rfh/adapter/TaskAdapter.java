package rfh.tianli.com.rfh.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.activity.TaskActivity;
import rfh.tianli.com.rfh.activity.TaskDetailActivity;
import rfh.tianli.com.rfh.domain.TaskInfo;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/21 on 11:11.
 * 描述：
 */

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private List<TaskInfo> taskInfoList;
    public TaskAdapter(Context context,List<TaskInfo> taskInfoList){
        this.context=context;
        this.taskInfoList=taskInfoList;
    }


    @Override
    public int getCount() {
        return taskInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_task,null);
            holder=new ViewHolder();
            holder.rl_item= (RelativeLayout) convertView.findViewById(R.id.rl_item);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_location= (TextView) convertView.findViewById(R.id.tv_location);
            holder.tv_time= (TextView) convertView.findViewById(R.id.tv_time);
            holder.iv_status= (ImageView) convertView.findViewById(R.id.iv_status);
            holder.tv_status= (TextView) convertView.findViewById(R.id.tv_status);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        TaskInfo taskInfo=taskInfoList.get(position);
        holder.tv_name.setText(taskInfo.getName());
        holder.tv_location.setText(taskInfo.getApartmentName());
        holder.tv_time.setText(gettime(taskInfo.getExecuteTime()));
        String status=taskInfo.getStatus();
        if(status.equals("1")){
            holder.iv_status.setImageResource(R.drawable.btn_no);
            holder.tv_status.setText("未开始");
            int color=context.getResources().getColor(R.color.white);
            holder.rl_item.setBackgroundColor(color);
        }else if(status.equals("2")){
            holder.iv_status.setImageResource(R.drawable.btn_ing);
            holder.tv_status.setText("进行中");
            int color=context.getResources().getColor(R.color.white);
            holder.rl_item.setBackgroundColor(color);
        }else{
            holder.iv_status.setImageResource(R.drawable.btn_over);
            holder.tv_status.setText("已完成");
            holder.rl_item.setBackgroundResource(R.drawable.squer);
        }
        OnClick(holder,position);
        return convertView;
    }


    class ViewHolder{
        private RelativeLayout rl_item;
        private TextView tv_name;
        private TextView tv_location;
        private TextView tv_time;
        private ImageView iv_status;
        private TextView tv_status;
    }

    //时间戳转正常时间
    private String gettime(long time){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;
    }


    private void OnClick(ViewHolder holder, final int position){
        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TaskDetailActivity.class);
                long taskId=taskInfoList.get(position).getId();
                String status=taskInfoList.get(position).getStatus();
                intent.putExtra("taskId",taskId);
                intent.putExtra("status",status);
                intent.putExtra("position",position);
//                context.startActivity(intent);
                int requestCode = 101;//请求码，可以随意写，只要和OnActivityResult中的请求码想对应就行
                ((Activity)context).startActivityForResult(intent,requestCode);
                ((Activity) TaskActivity.context).overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
            }
        });
    }

}
