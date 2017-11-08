package com.example.yandanfeng.latte.app;

import android.content.Context;

import java.util.HashMap;


/**
 * Created by Administrator on 2017-11-06
 */

public class Latte {
    /*
    * 对外的工具类
    * */
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();


    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
