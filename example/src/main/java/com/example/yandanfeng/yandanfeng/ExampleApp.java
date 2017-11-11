package com.example.yandanfeng.yandanfeng;

import android.app.Application;

import com.example.yandanfeng.latte.app.Latte;
import com.example.yandanfeng.latte.ec.icom.FontEcModule;
import com.example.yandanfeng.latte.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Administrator on 2017-11-06
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("HTTP://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
