package rfh.tianli.com.rfh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 10:07.
 * 描述：解决ScrollView嵌套ListView的方法
 */

public class ShowListView extends ListView {

    public ShowListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO 自动生成的构造函数存根
    }

    public ShowListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO 自动生成的构造函数存根
    }

    public ShowListView(Context context) {
        super(context);
        // TODO 自动生成的构造函数存根
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
