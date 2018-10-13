package com.predict.tide.black.tide.tideCode.utils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.predict.tide.black.tide.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by black on 2018/4/17.
 */

public class MUtils {
    public static String changeData(String time) {

        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long timel = new Long(Long.parseLong(time));

        String d = dateFormat.format(timel);
        String ll = d;
        String lasttime = d.split(" ")[1];
        String hour = lasttime.split(":")[0];

        if (hour.charAt(0) == '0') {
            hour = hour.substring(1);
        }
        return hour;
    }

    public static ArrayList<Integer> getMaxMin(ArrayList<Integer> lists) {
        ArrayList<Integer> maxmin = new ArrayList<>();
        int tempmin = lists.get(0);
        int tempmax = lists.get(0);
        int tempmini = 0;
        int tempmaxi = 0;
        for (int i = 0; i < lists.size(); i++) {
            if (tempmax < lists.get(i)) {
                tempmax = lists.get(i);
                tempmaxi = i;
            }
            if (tempmin > lists.get(i)) {
                tempmin = lists.get(i);
                tempmini = i;
            }
        }
        maxmin.add(tempmax + 50);
        maxmin.add(tempmin - 50);
        maxmin.add(tempmaxi);
        maxmin.add(tempmini);
        return maxmin;
    }


    public static boolean strNotnull(String str) {
        if (str != null && !str.equals("")) {
            return true;
        }
        return false;
    }

    public static ArrayList<String> getDateList(int qianhouDay) {
        ArrayList<String> datelists = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int millssecond = 24 * 60 * 60 * 1000;
        long nowdtime = System.currentTimeMillis();
        for (int i = 0 - qianhouDay; i < qianhouDay; i++) {
            Date date = new Date(nowdtime + (long) i * millssecond);
            String dateOk = simpleDateFormat.format(date);
            datelists.add(dateOk);
        }
        return datelists;
    }

    public static String getNowDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long nowdtime = System.currentTimeMillis();
        Date date = new Date(nowdtime);
        String dateok = simpleDateFormat.format(date);
        return dateok;
    }

    private static boolean isSuccess = false;

//    /**
//     * 打开相册，选择图片,可多选,限制最大的选择数量。
//     *
//     * @param activity
//     * @param requestCode
//     * @param isSingle       是否单选
//     * @param maxSelectCount 图片的最大选择数量，小于等于0时，不限数量，isSingle为false时才有用。
//     */
//    public static void openPhoto(Activity activity, int requestCode,
//                                 boolean isSingle, int maxSelectCount) {
//        ImageSelectorActivity.openActivity(activity, requestCode, isSingle, maxSelectCount);
//
//    }

    public static void addImageIntoView(ArrayList<String> path, ViewGroup llView, Context mContext){
        if (llView.getChildCount()>1){
            int count = llView.getChildCount();
            for (int i = 0; i < count-1; i++) {
                llView.removeViewAt(0);
            }
        }
        int pixel = llView.getWidth();
        int imagewidth = pixel/3-10;
        for (int i = 0; i < path.size(); i++) {
            ImageView iv = new ImageView(mContext);
            iv.setPadding(0,0,10,10);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(imagewidth,imagewidth-30);

            Glide.with(mContext)
                    .load(path.get(i))
                    .into(iv);
            llView.addView(iv,0,params);
        }

    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static DisplayImageOptions returnOptions(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)// 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20))// 设置成圆角图片
                .build();// 创建DisplayImageOptions对象
        return options;
    }

    public static ArrayList<Double> getCaculateTide(double lat){
        ArrayList<Double> list = new ArrayList<>();
        double shuju1 = Math.pow(Math.cos(lat),2);
        double shuju2 = Math.sin(lat+12.5) * 2.245 * 0.1 + 3.6373;
        double shuju3 = 36;
        double data = shuju1 *shuju2 * shuju3;
        list.add(data*4);
        list.add(data*6);
        list.add(data*12);
        return list;
    }

    public static ArrayList<Integer> getlistByAddress(double lat){
        ArrayList<Integer> list = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            double thita = 36*i;
            double sunH = 21.22* (Math.cos(Math.PI/2+Math.toRadians(thita*1.92857))+1) *Math.cos(Math.toRadians(lat - 5.1254));
            double earthH = 0.082 * (Math.cos(Math.PI/2+Math.toRadians(thita))+1) * Math.cos(Math.toRadians(lat - 23.5));
            double height =  sunH + earthH;

            list.add((int) Math.round(height));
        }

        return list;
    }


}
