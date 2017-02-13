package rfh.tianli.com.rfh.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.ByteArrayOutputStream;
import java.io.File;

import rfh.tianli.com.rfh.R;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/13 on 9:27.
 * 描述：
 */

public class Tools {


    /**
     * 加载图片
     */
    public static void getImage(Context context) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_main_errorpicture) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_main_errorpicture) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .showImageOnLoading(R.drawable.ic_main_nopicture).build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(options).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory().imageDownloader(new BaseImageDownloader(context))
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 加载图片
     */
    public static void getImage(Context context, int drawble) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageForEmptyUri(drawble) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(drawble) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .showImageOnLoading(drawble).build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(options).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory().imageDownloader(new BaseImageDownloader(context))
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);

    }

    /**
     * ScrollView中listview显示完全的方法
     *
     * @param listView
     */
//	public static void setListViewHeightBasedOnChildren(ListView listView) {
//		// 获取ListView对应的Adapter
//		ListAdapter listAdapter = listView.getAdapter();
//		if (listAdapter == null) {
//			return;
//		}
//
//		int totalHeight = 0;
//		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
//			// listAdapter.getCount()返回数据项的数目
//			View listItem = listAdapter.getView(i, null, listView);
//			// 计算子项View 的宽高
//			listItem.measure(0, 0);
//			// 统计所有子项的总高度
//			totalHeight += listItem.getMeasuredHeight();
//		}
//
//		ViewGroup.LayoutParams params = listView.getLayoutParams();
//		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//		// listView.getDividerHeight()获取子项间分隔符占用的高度
//		// params.height最后得到整个ListView完整显示需要的高度
//		listView.setLayoutParams(params);
//	}

    /**
     * 获取控件的高度或者宽度 isHeight=true则为测量该控件的高度，isHeight=false则为测量该控件的宽度
     *
     * @param view
     * @param isHeight
     * @return
     */
    public static int getViewHeight(View view, boolean isHeight) {
        int result;
        if (view == null)
            return 0;
        if (isHeight) {
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(h, 0);
            result = view.getMeasuredHeight();
        } else {
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(0, w);
            result = view.getMeasuredWidth();
        }
        return result;
    }

    /**
     * 得到指定大小，压缩后的图片。
     *
     * @param mcContext
     *            上下文
     * @param image
     *            原图片
     * @return 压缩后的图片
     */
    public static Bitmap getThumbnails(Context mcContext, Bitmap image) {
		/*
		 * getResources().getDimensionPixelSize 取出dimens中的值
		 */
        Bitmap bitmap;
        int width = mcContext.getResources().getDimensionPixelSize(R.dimen.regist_image_width);
        int height = mcContext.getResources().getDimensionPixelSize(R.dimen.regist_image_height);
		/*
		 * ThumbnailUtils.extractThumbnail 创建一个指定大小的缩略图
		 *
		 * @param source 源文件(Bitmap类型)
		 *
		 * @param width 压缩成的宽度
		 *
		 * @param height 压缩成的高度
		 */
        int h = image.getHeight();
        int w = image.getWidth();
        if (h > width || w > height) {
            bitmap = ThumbnailUtils.extractThumbnail(image, width, height);
        } else {
            bitmap = image;
        }
        return bitmap;
    }

    /**
     * 得到指定大小，压缩后的图片。
     *
     * @param mcContext
     *            上下文
     * @param image
     *            原图片
     * @return 压缩后的图片
     */
    public static Bitmap getThumbnails(Context mcContext, Bitmap image, int widths, int heights) {
		/*
		 * getResources().getDimensionPixelSize 取出dimens中的值
		 */
        Bitmap bitmap;
        int width = mcContext.getResources().getDimensionPixelSize(widths);
        int height = mcContext.getResources().getDimensionPixelSize(heights);
		/*
		 * ThumbnailUtils.extractThumbnail 创建一个指定大小的缩略图
		 *
		 * @param source 源文件(Bitmap类型)
		 *
		 * @param width 压缩成的宽度
		 *
		 * @param height 压缩成的高度
		 */
        int h = image.getHeight();
        int w = image.getWidth();
        if (h > width || w > height) {
            bitmap = ThumbnailUtils.extractThumbnail(image, width, height);
        } else {
            bitmap = image;
        }
        return bitmap;
    }

    /**
     * 得到指定大小，压缩后的图片。
     *
     * @param mcContext
     *            上下文
     * @param image
     *            原图片
     * @return 压缩后的图片
     */
    public static Bitmap getThumbnail(Context mcContext, Bitmap image) {
		/*
		 * getResources().getDimensionPixelSize 取出dimens中的值
		 */
        Bitmap bitmap;
        int width = mcContext.getResources().getDimensionPixelSize(R.dimen.regist_table_width);
        int height = mcContext.getResources().getDimensionPixelSize(R.dimen.regist_table_height);
		/*
		 * ThumbnailUtils.extractThumbnail 创建一个指定大小的缩略图
		 *
		 * @param source 源文件(Bitmap类型)
		 *
		 * @param width 压缩成的宽度
		 *
		 * @param height 压缩成的高度
		 */
        int h = image.getHeight();
        int w = image.getWidth();
        if (h > width || w > height) {
            bitmap = ThumbnailUtils.extractThumbnail(image, width, height);
        } else {
            bitmap = image;
        }
        return bitmap;
    }

    /**
     * 把Bitmap转换成字节数组
     *
     * @param bitmap
     * @return
     */
    public static byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * 把字节数组转换成Bitmap
     *
     * @param temp
     * @return
     */
    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * 把一个Drawable转换成Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 把Bitmap转换成Drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable Bitmap_Drawable(Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(bitmap);
        return drawable;
    }



    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //清理缓存
    public static void cleanTemp(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                file2.delete();
            }
        }
    }
}
