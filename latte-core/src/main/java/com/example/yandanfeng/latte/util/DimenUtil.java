package com.example.yandanfeng.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.yandanfeng.latte.app.Latte;

/**
 * Created by Administrator on 2017-11-09
 */

public class DimenUtil {

    //得到屏幕的获取宽
    public static int getScreenWith() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }


    //得到屏幕的高
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels ;
    }




}
