package rfh.tianli.com.rfh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 16:40.
 * 描述：FragmentPagerAdapter
 */

public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fs;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fs) {
        super(fm);
        this.fs=fs;
    }

    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }

    @Override
    public int getCount() {
        return fs.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
