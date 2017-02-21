package rfh.tianli.com.rfh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.domain.TaskInfo;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/21 on 11:11.
 * 描述：
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private Context context;
    private List<TaskInfo> taskInfoList;
    public TaskAdapter(Context context,List<TaskInfo> taskInfoList){
        this.context=context;
        this.taskInfoList=taskInfoList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_task,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
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
    }


    @Override
    public int getItemCount() {
        return taskInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rl_item;
        private TextView tv_name;
        private TextView tv_location;
        private TextView tv_time;
        private ImageView iv_status;
        private TextView tv_status;


        public MyViewHolder(View itemView) {
            super(itemView);
            rl_item= (RelativeLayout) itemView.findViewById(R.id.rl_item);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            tv_location= (TextView) itemView.findViewById(R.id.tv_location);
            tv_time= (TextView) itemView.findViewById(R.id.tv_time);
            iv_status= (ImageView) itemView.findViewById(R.id.iv_status);
            tv_status= (TextView) itemView.findViewById(R.id.tv_status);
        }
    }



    private String gettime(long time){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;
    }
}
