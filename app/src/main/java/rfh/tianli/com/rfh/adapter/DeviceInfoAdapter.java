package rfh.tianli.com.rfh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.domain.DeviceDataInfo;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/17 on 18:00.
 * 描述：
 */

public class DeviceInfoAdapter extends BaseAdapter {


    private Context context;
    private List<DeviceDataInfo> deviceDataInfos;

    //7种类型
    private final int VIEW_TYPE = 7;

    private final int TYPE_1 = 0;
    private final int TYPE_2 = 1;
    private final int TYPE_3 = 2;
    private final int TYPE_4 = 3;
    private final int TYPE_5 = 4;
    private final int TYPE_6 = 5;
    private final int TYPE_7 = 6;

    public static TimePickerView pvTime1,pvTime2;



    public DeviceInfoAdapter(Context context, List<DeviceDataInfo> deviceDataInfos) {
        this.context = context;
        this.deviceDataInfos = deviceDataInfos;
        pvTime1=new TimePickerView(context,TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime1.setTime(new Date());
        pvTime1.setCyclic(false);
        pvTime1.setCancelable(true);

        pvTime2=new TimePickerView(context,TimePickerView.Type.MONTH_DAY_HOUR_MIN);
        pvTime2.setTime(new Date());
        pvTime2.setCyclic(false);
        pvTime2.setCancelable(true);
    }


    @Override
    public int getCount() {
        return deviceDataInfos.size();
    }

    @Override
    public DeviceDataInfo getItem(int position) {
        return deviceDataInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        String type = getItem(position).getWidgetType();
        switch (type) {
            case "text":
                return TYPE_1;
            case "textarea":
                return TYPE_2;
            case "checkbox":
                return TYPE_3;
            case "integer":
                return TYPE_4;
            case "decimal":
                return TYPE_5;
            case "date":
                return TYPE_6;
            case "datetime":
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
        ViewHolder1 viewHolder1 = null;
        ViewHolder2 viewHolder2 = null;
        ViewHolder3 viewHolder3 = null;
        ViewHolder4 viewHolder4 = null;
        ViewHolder5 viewHolder5 = null;
        ViewHolder6 viewHolder6 = null;
        ViewHolder7 viewHolder7 = null;
        int type = getItemViewType(position);
        DeviceDataInfo deviceDataInfo = getItem(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_1:
                    viewHolder1 = new ViewHolder1();
                    convertView = View.inflate(context, R.layout.item_type_text, null);
                    viewHolder1.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                    viewHolder1.et_desc = (EditText) convertView.findViewById(R.id.et_desc);
                    convertView.setTag(R.layout.item_type_text, viewHolder1);
                    break;
                case TYPE_2:
                    viewHolder2 = new ViewHolder2();
                    convertView = View.inflate(context, R.layout.item_type_textarea, null);
                    viewHolder2.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                    viewHolder2.et_desc = (EditText) convertView.findViewById(R.id.et_desc);
                    convertView.setTag(R.layout.item_type_textarea, viewHolder2);
                    break;
                case TYPE_3:
                    viewHolder3 = new ViewHolder3();
                    convertView = View.inflate(context, R.layout.item_type_checkbox, null);
                    viewHolder3.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                    viewHolder3.cb = (CheckBox) convertView.findViewById(R.id.cb);
                    convertView.setTag(R.layout.item_type_checkbox, viewHolder3);
                    break;
                case TYPE_4:
                    viewHolder4 = new ViewHolder4();
                    convertView = View.inflate(context, R.layout.item_type_integer, null);
                    viewHolder4.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                    viewHolder4.tv_code = (TextView) convertView.findViewById(R.id.tv_code);
                    viewHolder4.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
                    viewHolder4.et_value = (EditText) convertView.findViewById(R.id.et_value);
                    convertView.setTag(R.layout.item_type_integer, viewHolder4);
                    break;
                case TYPE_5:
                    viewHolder5 = new ViewHolder5();
                    convertView = View.inflate(context, R.layout.item_type_decimal, null);
                    viewHolder5.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                    viewHolder5.tv_code = (TextView) convertView.findViewById(R.id.tv_code);
                    viewHolder5.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
                    viewHolder5.et_value = (EditText) convertView.findViewById(R.id.et_value);
                    convertView.setTag(R.layout.item_type_decimal, viewHolder5);
                    break;
                case TYPE_6:
                    viewHolder6 = new ViewHolder6();
                    convertView = View.inflate(context, R.layout.item_type_date, null);
                    viewHolder6.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                    viewHolder6.tv_datetime = (TextView) convertView.findViewById(R.id.tv_datetime);

                    final ViewHolder6 finalViewHolder = viewHolder6;
                    pvTime1.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                            finalViewHolder.tv_datetime.setText(format.format(date));
                        }
                    });
                    viewHolder6.tv_datetime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pvTime1.show();
                        }
                    });
                    convertView.setTag(R.layout.item_type_date, viewHolder6);
                case TYPE_7:
                    viewHolder7 = new ViewHolder7();
                    convertView = View.inflate(context, R.layout.item_type_datetime, null);
                    viewHolder7.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                    viewHolder7.tv_datetime = (TextView) convertView.findViewById(R.id.tv_datetime);

                    final ViewHolder7 finalViewHolder1 = viewHolder7;
                    pvTime2.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                            SimpleDateFormat format = new SimpleDateFormat("MM月dd日HH时mm分");
                            finalViewHolder1.tv_datetime.setText(format.format(date));
                        }
                    });

                    viewHolder7.tv_datetime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pvTime2.show();
                        }
                    });


                    convertView.setTag(R.layout.item_type_datetime, viewHolder7);
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPE_1:
                    viewHolder1 = (ViewHolder1) convertView.getTag(R.layout.item_type_text);
                    break;
                case TYPE_2:
                    viewHolder2 = (ViewHolder2) convertView.getTag(R.layout.item_type_textarea);
                    break;
                case TYPE_3:
                    viewHolder3 = (ViewHolder3) convertView.getTag(R.layout.item_type_checkbox);
                    break;
                case TYPE_4:
                    viewHolder4 = (ViewHolder4) convertView.getTag(R.layout.item_type_integer);
                    break;
                case TYPE_5:
                    viewHolder5 = (ViewHolder5) convertView.getTag(R.layout.item_type_decimal);
                    break;
                case TYPE_6:
                    viewHolder6 = (ViewHolder6) convertView.getTag(R.layout.item_type_date);
                    break;
                case TYPE_7:
                    viewHolder7 = (ViewHolder7) convertView.getTag(R.layout.item_type_datetime);
                    break;
                default:
                    break;
            }
        }


        switch (type) {
            case TYPE_1:
                if (viewHolder1 == null) {
                    viewHolder1 = new ViewHolder1();
                    convertView = View.inflate(context, R.layout.item_type_text, null);
                    viewHolder1.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                    viewHolder1.et_desc = (EditText) convertView.findViewById(R.id.et_desc);
                    convertView.setTag(R.layout.item_type_text, viewHolder1);
                }
                viewHolder1.tv_desc.setText(deviceDataInfo.getName());
                break;
            case TYPE_2:
                if (viewHolder2 == null) {
                    viewHolder2 = new ViewHolder2();
                    convertView = View.inflate(context, R.layout.item_type_textarea, null);
                    viewHolder2.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                    viewHolder2.et_desc = (EditText) convertView.findViewById(R.id.et_desc);
                    convertView.setTag(R.layout.item_type_textarea, viewHolder2);
                }
                viewHolder2.tv_desc.setText(deviceDataInfo.getName());
                break;
            case TYPE_3:
                if (viewHolder3 == null) {
                    viewHolder3 = new ViewHolder3();
                    convertView = View.inflate(context, R.layout.item_type_checkbox, null);
                    viewHolder3.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                    viewHolder3.cb = (CheckBox) convertView.findViewById(R.id.cb);
                    convertView.setTag(R.layout.item_type_checkbox, viewHolder3);
                }
                viewHolder3.tv_name.setText(deviceDataInfo.getName());
                break;
            case TYPE_4:
                if (viewHolder4 == null) {
                    viewHolder4 = new ViewHolder4();
                    convertView = View.inflate(context, R.layout.item_type_integer, null);
                    viewHolder4.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                    viewHolder4.tv_code = (TextView) convertView.findViewById(R.id.tv_code);
                    viewHolder4.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
                    viewHolder4.et_value = (EditText) convertView.findViewById(R.id.et_value);
                    convertView.setTag(R.layout.item_type_integer, viewHolder4);
                }
                viewHolder4.tv_name.setText(deviceDataInfo.getName());
                if(!(deviceDataInfo.getCode()).equals("null")){
                    viewHolder4.tv_code.setText("(" + deviceDataInfo.getCode() + ")");
                }else{
                    viewHolder4.tv_code.setVisibility(View.INVISIBLE);
                }
                if (!(deviceDataInfo.getUnit()).equals("null")) {
                    viewHolder4.tv_unit.setText("(" + deviceDataInfo.getUnit() + ")");
                } else {
                    viewHolder4.tv_unit.setVisibility(View.INVISIBLE);
                }
                break;

            case TYPE_5:
                if (viewHolder5 == null) {
                    viewHolder5 = new ViewHolder5();
                    convertView = View.inflate(context, R.layout.item_type_decimal, null);
                    viewHolder5.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                    viewHolder5.tv_code = (TextView) convertView.findViewById(R.id.tv_code);
                    viewHolder5.tv_unit = (TextView) convertView.findViewById(R.id.tv_unit);
                    viewHolder5.et_value = (EditText) convertView.findViewById(R.id.et_value);
                    convertView.setTag(R.layout.item_type_decimal, viewHolder5);
                }
                viewHolder5.tv_name.setText(deviceDataInfo.getName());
                if(!(deviceDataInfo.getCode()).equals("null")){
                    viewHolder5.tv_code.setText("(" + deviceDataInfo.getCode() + ")");
                }else{
                    viewHolder5.tv_code.setVisibility(View.INVISIBLE);
                }
                if (!(deviceDataInfo.getUnit()).equals("null")) {
                    viewHolder5.tv_unit.setText("(" + deviceDataInfo.getUnit() + ")");
                } else {
                    viewHolder5.tv_unit.setVisibility(View.INVISIBLE);
                }
                break;
            case TYPE_6:
                if (viewHolder6 == null) {
                    viewHolder6 = new ViewHolder6();
                    convertView = View.inflate(context, R.layout.item_type_date, null);
                    viewHolder6.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                    viewHolder6.tv_datetime = (TextView) convertView.findViewById(R.id.tv_datetime);
                    convertView.setTag(R.layout.item_type_date, viewHolder6);


                    final ViewHolder6 finalViewHolder = viewHolder6;
                    pvTime1.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                            finalViewHolder.tv_datetime.setText(format.format(date));
                        }
                    });
                    viewHolder6.tv_datetime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pvTime1.show();
                        }
                    });
                }
                viewHolder6.tv_desc.setText(deviceDataInfo.getName());
                break;
            case TYPE_7:
                if (viewHolder7 == null) {
                    viewHolder7 = new ViewHolder7();
                    convertView = View.inflate(context, R.layout.item_type_datetime, null);
                    viewHolder7.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                    viewHolder7.tv_datetime = (TextView) convertView.findViewById(R.id.tv_datetime);

                    final ViewHolder7 finalViewHolder1 = viewHolder7;
                    pvTime2.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                            SimpleDateFormat format = new SimpleDateFormat("MM月dd日HH时mm分");
                            finalViewHolder1.tv_datetime.setText(format.format(date));
                        }
                    });

                    viewHolder7.tv_datetime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pvTime2.show();
                        }
                    });
                    convertView.setTag(R.layout.item_type_datetime, viewHolder7);
                }
                viewHolder7.tv_desc.setText(deviceDataInfo.getName());
                break;
            default:
                break;
        }
        return convertView;
    }

    public class ViewHolder1 {
        public TextView tv_desc;
        public EditText et_desc;
    }

    public class ViewHolder2 {
        public TextView tv_desc;
        public TextView et_desc;
    }

    public class ViewHolder3 {
        public TextView tv_name;
        public CheckBox cb;
    }

    public class ViewHolder4 {
        public TextView tv_name;
        public TextView tv_code;
        public TextView tv_unit;
        public EditText et_value;
    }

    public class ViewHolder5 {
        public TextView tv_name;
        public TextView tv_code;
        public TextView tv_unit;
        public EditText et_value;
    }

    public class ViewHolder6 {
        public TextView tv_desc;
        public TextView tv_datetime;
    }

    public class ViewHolder7 {
        public TextView tv_desc;
        public TextView tv_datetime;
    }


}
