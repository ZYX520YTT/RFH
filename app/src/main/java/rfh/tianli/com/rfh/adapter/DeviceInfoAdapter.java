package rfh.tianli.com.rfh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import rfh.tianli.com.rfh.domain.DeviceDataInfo;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/17 on 18:00.
 * 描述：
 */

public class DeviceInfoAdapter extends BaseAdapter{


    private Context context;
    private List<DeviceDataInfo> deviceDataInfos;

    //7中类型
    private final int VIEW_TYPE=7;


    private final int TYPE_1=0;
    private final int TYPE_2=1;
    private final int TYPE_3=2;
    private final int TYPE_4=3;
    private final int TYPE_5=4;
    private final int TYPE_6=5;
    private final int TYPE_7=6;


    public DeviceInfoAdapter(Context context,List<DeviceDataInfo> deviceDataInfos){
        this.context=context;
        this.deviceDataInfos=deviceDataInfos;
    }


    @Override
    public int getCount() {
        return deviceDataInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceDataInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        int p=position;
        switch (p){
            case 0:
                return TYPE_1;
            case 1:
                return TYPE_2;
            case 2:
                return TYPE_3;
            case 3:
                return TYPE_4;
            case 4:
                return TYPE_5;
            case 5:
                return TYPE_6;
            case 6:
                return TYPE_7;
            default:
                return TYPE_1;
        }
    }


    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public class ViewHolder1{
        public TextView tv_desc;
        public EditText et_desc;
    }

    public class ViewHolder2{
        public TextView tv_desc;
        public TextView et_desc;
    }

    public class ViewHolder3{
        public TextView tv_name;
        public CheckBox cb;
    }

    public class ViewHolder4{
        public TextView tv_name;
        public TextView tv_code;
        public TextView tv_unit;
        public EditText et_value;
    }

    public class ViewHolder5{
        public TextView tv_name;
        public TextView tv_code;
        public TextView tv_unit;
        public EditText et_value;
    }

    public class ViewHolder6{
        public TextView tv_desc;
        public TextView tv_date;
    }

    public class ViewHolder7{
        public TextView tv_desc;
        public TextView tv_datetime;
    }


}
