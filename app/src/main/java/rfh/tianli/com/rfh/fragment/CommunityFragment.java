package rfh.tianli.com.rfh.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rfh.tianli.com.rfh.R;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 16:48.
 * 描述：社区互动页面
 */

public class CommunityFragment extends Fragment {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        TextView textView=new TextView(getContext());
        textView.setText("社区互动");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(32);
        textView.setTextColor(getResources().getColor(R.color.main_ra_light));
        return textView;
    }


}
