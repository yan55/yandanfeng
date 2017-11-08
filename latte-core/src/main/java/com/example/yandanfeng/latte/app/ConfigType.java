package com.example.yandanfeng.latte.app;

/**
 * Created by Administrator on 2017-11-06
 */


/*
    枚举类在应用中是唯一的单列模式，只能初始化一次
* */
public enum ConfigType {
    API_HOST,//网路请求域名
    APPLICATION_CONTEXT,//全局的上下文
    CONFIG_READY,//控制初始化配置完成没有
    ICON,



}
