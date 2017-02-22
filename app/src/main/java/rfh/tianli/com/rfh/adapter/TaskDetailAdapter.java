package rfh.tianli.com.rfh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.domain.TaskDetailInfo;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/22 on 15:24.
 * 描述：
 */

public class TaskDetailAdapter extends RecyclerView.Adapter<TaskDetailAdapter.MyViewHolder> {

    private Context context;
    private List<TaskDetailInfo> taskDetailInfoList;
    public TaskDetailAdapter(Context context, List<TaskDetailInfo> taskDetailInfoList){
        this.context=context;
        this.taskDetailInfoList=taskDetailInfoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.item_taskdetail,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TaskDetailInfo taskDetailInfo=taskDetailInfoList.get(position);
        holder.tv_name.setText(taskDetailInfo.getName());
        holder.tv_location.setText(taskDetailInfo.getLocation());
        String status=taskDetailInfo.getPatrolStatus();
        if(status.equals("1")){
            holder.iv_status.setImageResource(R.drawable.btn_over);
            holder.tv_status.setText("已巡查");
            holder.rl_item.setBackgroundResource(R.drawable.squer);
        }else{
            holder.iv_status.setImageResource(R.drawable.btn_no);
            holder.tv_status.setText("未巡查");
            int color=context.getResources().getColor(R.color.white);
            holder.rl_item.setBackgroundColor(color);
        }
    }


    @Override
    public int getItemCount() {
        return taskDetailInfoList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout rl_item;
        private TextView tv_name;
        private TextView tv_location;
        private ImageView iv_status;
        private TextView tv_status;

        public MyViewHolder(View itemView) {
            super(itemView);
            rl_item= (RelativeLayout) itemView.findViewById(R.id.rl_item);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            tv_location= (TextView) itemView.findViewById(R.id.tv_location);
            iv_status= (ImageView) itemView.findViewById(R.id.iv_status);
            tv_status= (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
