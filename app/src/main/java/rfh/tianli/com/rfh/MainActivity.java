package rfh.tianli.com.rfh;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rfh.tianli.com.rfh.adapter.MyFragmentAdapter;
import rfh.tianli.com.rfh.fragment.CommunityFragment;
import rfh.tianli.com.rfh.fragment.PayFragment;
import rfh.tianli.com.rfh.fragment.ServiceFragment;
import rfh.tianli.com.rfh.fragment.SettingFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private Boolean isExit=false;//判断是否要退出程序
    public static Context context;

    @ViewInject(R.id.radioGroup)
    private RadioGroup radioGroup;
    @ViewInject(R.id.rb_service)
    private RadioButton rb_service;
    @ViewInject(R.id.rb_pay)
    private RadioButton rb_pay;
    @ViewInject(R.id.rb_community)
    private RadioButton rb_community;
    @ViewInject(R.id.rb_setting)
    private RadioButton rb_setting;
    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        ((NApplication) this.getApplication()).addActivity(this);//加入这个Activity到集合中
        ViewUtils.inject(this);
        Init();
    }


    private void Init(){
        setupFragment();


        //给4个功能设置点击事件
        rb_service.setOnClickListener(this);//物业服务
        rb_pay.setOnClickListener(this);//物业缴费
        rb_community.setOnClickListener(this);//社区互动
        rb_setting.setOnClickListener(this);//个人设置
    }


    //将Fragment加入到集合中并显示出来
    private void setupFragment(){
        FragmentManager fm=getSupportFragmentManager();
        List<Fragment> fs=new ArrayList<>();
        fs.add(new ServiceFragment());
        fs.add(new PayFragment());
        fs.add(new CommunityFragment());
        fs.add(new SettingFragment());

        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter(fm,fs);
        viewPager.setAdapter(myFragmentAdapter);


        //初始选中第1页--->首页
        viewPager.setCurrentItem(0,false);
        rb_service.setChecked(true);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rb_service.setChecked(true);
                        break;
                    case 1:
                        rb_pay.setChecked(true);
                        break;
                    case 2:
                        rb_community.setChecked(true);
                        break;
                    case 3:
                        rb_setting.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        if(isExit){
            super.onBackPressed();
            ((NApplication)this.getApplication()).destoryAllActivity();
            finish();
        }else{
            isExit=true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit=false;
                }
            },2000);
            Toast.makeText(context,"再点击一次退出程序",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_service:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.rb_pay:
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.rb_community:
                viewPager.setCurrentItem(2,false);
                break;
            case R.id.rb_setting:
                viewPager.setCurrentItem(3,false);
                break;
            default:
                break;
        }
    }
}
