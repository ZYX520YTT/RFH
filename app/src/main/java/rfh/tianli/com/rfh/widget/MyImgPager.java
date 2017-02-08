package rfh.tianli.com.rfh.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import rfh.tianli.com.rfh.R;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/8 on 15:04.
 * 描述：/**
 * 图片轮播功能,可以xml文件创建,也可以通过java代码创建.如果你的整个布局有下拉刷新功能,请用java代码创建该控件,手动添加到容器中
 *
 * 此控件用到了universal-image-loader-1.9.5第三方jar包,使用前请先导包,并且需要在你的Application.
 * java中配置如下代码:
 *
 * DisplayImageOptions options = new DisplayImageOptions.Builder()
 * .showImageForEmptyUri(R.drawable.news_list_02) // 设置图片Uri为空或是错误的时候显示的图片
 * .showImageOnFail(R.drawable.news_list_02) // 设置图片加载或解码过程中发生错误显示的图片
 * .cacheInMemory(true) // 设置下载的图片是否缓存在内存中 .cacheOnDisk(false) //
 * 设置下载的图片是否缓存在SD卡中 .showImageOnLoading(R.drawable.news_list_02).build(); //
 * 创建配置过得DisplayImageOption对象
 *
 * ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
 * .defaultDisplayImageOptions(options) .threadPriority(Thread.NORM_PRIORITY -
 * 2).denyCacheImageMultipleSizesInMemory() .imageDownloader(new
 * BaseImageDownloader(this))
 * .tasksProcessingOrder(QueueProcessingType.LIFO).build();
 * ImageLoader.getInstance().init(config);
 */

public class MyImgPager extends LinearLayout implements OnPageChangeListener {

    Context context;
    /*
     * 轮播图布局
     */
    private View view;
    /*
     * 滚动圆点
     */
    private View v;
    /*
     * 图片容器
     */
    private ViewPager vp;
    /*
     * 图片描述
     */
    private TextView tv_desc;
    private RelativeLayout ll;
    /*
     * 滚动圆点容器
     */
    private RelativeLayout rlyt_hint;
    private LinearLayout ll_point;
    private MyPagerAdapter adapter;
    private LayoutParams params;
    private List<Integer> imgResources = new ArrayList<Integer>();
    private List<String> imgPaths = new ArrayList<String>();
    private List<String> descs = new ArrayList<String>();
    private int imgCount;
    private ImageView[] imgs;
    private int count;
    private boolean flag = false;
    ImageCycleViewListener imageOnClickListener;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            vp.setCurrentItem(vp.getCurrentItem() + 1);
            super.handleMessage(msg);
        }
    };

    @SuppressLint("NewApi")
    public MyImgPager(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public MyImgPager(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MyImgPager(final Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        view = View.inflate(context, R.layout.item_viewpager, null);
        this.addView(view);
        vp = (ViewPager) view.findViewById(R.id.vp);
        tv_desc = (TextView) view.findViewById(R.id.tv_desc);
        ll = (RelativeLayout) view.findViewById(R.id.ll);
        ll_point = (LinearLayout) view.findViewById(R.id.ll_point);
        rlyt_hint = (RelativeLayout) view.findViewById(R.id.rlyt_hint);
    }

    // 不要下面的那个半透明mask
    public void removeHint() {
        rlyt_hint.setBackgroundResource(0);
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            if (imgs[position % imgCount] == null) {
                imgs[position % imgCount] = new ImageView(getContext());
                imgs[position % imgCount].setScaleType(ImageView.ScaleType.FIT_XY);
                if (imgResources.size() != 0) {
                    imgs[position % imgCount].setImageResource(imgResources.get(position % imgCount));
                }
                if (imgPaths.size() != 0) {
                    ImageLoader.getInstance().displayImage(imgPaths.get(position % imgCount),
                            imgs[position % imgCount]);
                }
                imgs[position % imgCount].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageOnClickListener.onImageClick(position % imgCount, v);
                    }
                });
            }
            try {
                boolean isExit = false;
                for (int i = 0; i < vp.getChildCount(); i++) {
                    if (vp.getChildAt(i).equals(imgs[position % imgCount])) {
                        vp.addView(imgs[position % imgCount]);
                        isExit = true;
                        break;
                    }
                }
                if (!isExit) {
                    vp.addView(imgs[position % imgCount]);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            return imgs[position % imgCount];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            vp.removeView(imgs[position % imgCount]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newPosition = position % imgCount;
        ll_point.getChildAt(count).setEnabled(false);
        ll_point.getChildAt(newPosition).setEnabled(true);
        if (descs != null && descs.size() > 0) {
            tv_desc.setText(descs.get(newPosition));
        }
        count = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
            // 开始图片滚动
            flag = true;
        } else {
            // 停止图片滚动
            flag = false;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置轮播图点击事件
     *
     * @param imgUrls
     *            图片地址
     * @param imgDescs
     *            图片描述
     * @param imageOnClickListener
     *            点击事件监听器
     * @param flag
     *            是否需要自动播放
     */
    public void setImgUrlAndOnClickListener(List<String> imgUrls, List<String> imgDescs,
                                            ImageCycleViewListener imageOnClickListener, boolean flag) {
        imgPaths = imgUrls;
        descs = imgDescs;
        this.imageOnClickListener = imageOnClickListener;
        imgCount = imgUrls.size();
        if (imgCount > 0) {
            this.setVisibility(VISIBLE);
            imgs = new ImageView[imgCount];
            for (int i = 0; i < imgCount; i++) {
                v = new View(getContext());
                v.setBackgroundResource(R.drawable.point_bg);
                params = new LayoutParams(20, 20);
                if (i != 0) {
                    params.setMargins(5, 0, 0, 0);
                }
                v.setEnabled(false);
                ll_point.addView(v, params);
            }

            vp.addOnPageChangeListener(this);

            ll_point.getChildAt(count).setEnabled(true);
            if (descs == null || descs.size() <= 0) {
                tv_desc.setVisibility(GONE);
            } else {
                tv_desc.setVisibility(VISIBLE);
                tv_desc.setText(descs.get(count));
            }
            adapter = new MyPagerAdapter();
            vp.setAdapter(adapter);
            vp.setCurrentItem(10000);
            this.flag = flag;
            new Thread(new MyThread()).start();
        } else {
            this.setVisibility(GONE);
        }
    }

    /**
     * 设置轮播图点击事件
     *
     * @param imgResources
     *            图片id
     * @param imgDescs
     *            图片描述
     * @param imageOnClickListener
     *            点击事件监听器
     * @param flag
     *            是否需要自动播放
     */
    public void setImgResourcesAndOnClickListener(List<Integer> imgResources, List<String> imgDescs,
                                                  ImageCycleViewListener imageOnClickListener, boolean flag) {
        this.imgResources = imgResources;
        descs = imgDescs;
        this.imageOnClickListener = imageOnClickListener;
        imgCount = imgResources.size();
        if (imgCount > 0) {
            this.setVisibility(VISIBLE);
            imgs = new ImageView[imgCount];
            for (int i = 0; i < imgCount; i++) {
                v = new View(getContext());
                v.setBackgroundResource(R.drawable.point_bg);
                params = new LayoutParams(20, 20);
                if (i != 0) {
                    params.setMargins(5, 0, 0, 0);
                }
                v.setEnabled(false);
                ll_point.addView(v, params);
            }

            vp.addOnPageChangeListener(this);

            ll_point.getChildAt(count).setEnabled(true);
            if (descs == null || descs.size() <= 0) {
                tv_desc.setVisibility(GONE);
            } else {
                tv_desc.setVisibility(VISIBLE);
                tv_desc.setText(descs.get(count));
            }
            adapter = new MyPagerAdapter();
            vp.setAdapter(adapter);
            vp.setCurrentItem(10000);
            this.flag = flag;
            new Thread(new MyThread()).start();
        } else {
            this.setVisibility(GONE);
        }
    }

    /**
     * 设置图片停止播放
     */
    public void setStopPlay() {
        flag = false;
    }

    /**
     * 点击事件的回调接口
     */
    public interface ImageCycleViewListener {
        /**
         * 点击事件的回调方法
         */
        public void onImageClick(int position, View imageView);
    }

    /**
     * 图片自动播放的线程
     */
    class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                SystemClock.sleep(3000);
                if (flag) {
                    handler.sendEmptyMessage(0);
                }
            }
        }
    }
}
