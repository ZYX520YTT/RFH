package rfh.tianli.com.rfh.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import rfh.tianli.com.rfh.MainActivity;
import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.activity.ScanActivity;
import rfh.tianli.com.rfh.adapter.ServiceNewsAdapter;
import rfh.tianli.com.rfh.domain.NewsInfo;
import rfh.tianli.com.rfh.widget.MyImgPager;
import rfh.tianli.com.rfh.widget.ShowListView;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/7 on 16:48.
 * 描述：物业服务页面
 */

public class ServiceFragment extends Fragment implements MyImgPager.ImageCycleViewListener, EasyPermissions.PermissionCallbacks {

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    private Context context;
    private View view;

    /******客服*****/
    @ViewInject(R.id.tv_customer)
    private TextView tv_customer;
    @ViewInject(R.id.iv_customer)
    private ImageView iv_customer;

    /*****轮播图ViewPager******/
    @ViewInject(R.id.myimpager)
    private MyImgPager myimpager;

    @ViewInject(R.id.iv_ownerselect)
    private ImageButton iv_ownerselect;//业主查询
    @ViewInject(R.id.iv_repairsonline)
    private ImageButton iv_repairsonline;//在线报修
    @ViewInject(R.id.iv_afforest)
    private ImageButton iv_afforest;//绿化保洁
    @ViewInject(R.id.iv_safetyconstruction)
    private ImageButton iv_safetyconstruction;//安全建设
    @ViewInject(R.id.iv_communiityannouncement)
    private ImageButton iv_communiityannouncement;//社区公告
    @ViewInject(R.id.iv_pay_inquire)
    private ImageButton iv_pay_inquire;//缴费查询
    @ViewInject(R.id.iv_basicseveral)
    private ImageButton iv_basicseveral;//基础服务
    @ViewInject(R.id.iv_qrcodeinquire)
    private ImageButton iv_qrcodeinquire;//二维码巡查


    /*****新闻列表*******/
    @ViewInject(R.id.ls_new)
    private ShowListView ls_new;






    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service,container,false);
        ViewUtils.inject(this, view);
        Init();
        return view;
    }

    private void Init(){

        InitData();


        //点击扫描二维码
        iv_qrcodeinquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ScanActivity.class));
                ((Activity) MainActivity.context).overridePendingTransition(R.anim.in_left_in,R.anim.in_right_out);
            }
        });
    }

    //初始化数据
    private void InitData(){

        /***************轮播测试*********************/
        List<Integer> imgResources = new ArrayList<>();
        List<String> imgDescs = new ArrayList<>();


        imgResources.add(R.drawable.pic_3);
        imgResources.add(R.drawable.pic4);
        imgResources.add(R.drawable.pic4);

        myimpager.setImgResourcesAndOnClickListener(imgResources,imgDescs,this,true);



        /***************轮播测试******************/


        /*****************新闻列表测试*************************/
        List<NewsInfo> newsInfos=new ArrayList<>();
        for(int i=0;i<5;i++){
            if(i%3==0){
                NewsInfo newsInfo=new NewsInfo(R.drawable.pic_1,"广州天力物业参观碧桂园天玺端高端大会1");
                newsInfos.add(newsInfo);
            }else if(i%3==1){
                NewsInfo newsInfo=new NewsInfo(R.drawable.pic_2,"广州天力物业参观碧桂园天玺端高端大会2");
                newsInfos.add(newsInfo);
            }else{
                NewsInfo newsInfo=new NewsInfo(R.drawable.pic_3,"广州天力物业参观碧桂园天玺端高端大会3");
                newsInfos.add(newsInfo);
            }
        }


        ls_new.setAdapter(new ServiceNewsAdapter(context,newsInfos));
        /*****************新闻列表测试*************************/

    }


    @Override
    public void onImageClick(int position, View imageView) {

    }




    @Override
    public void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(context, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
}
