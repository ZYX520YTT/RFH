package rfh.tianli.com.rfh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import rfh.tianli.com.rfh.R;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/9 on 18:13.
 * 描述：
 */

public class GroupAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;
    private int index;

    public GroupAdapter(Context context, List<String> list, int index) {
        this.context = context;
        this.list = list;
        this.index = index;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        holder.tv_name.setText(list.get(position));
        if (index == position) {
            holder.tv_name.setBackgroundResource(R.drawable.bg_regist_item);
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.white));
        }else {
            holder.tv_name.setBackgroundResource(0);
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.title_text_gray));
        }
        return convertView;
    }

    private class ViewHolder {
        public TextView tv_name;
    }


    public void setIndex(int index){
        this.index = index;
        notifyDataSetChanged();
    }
}
