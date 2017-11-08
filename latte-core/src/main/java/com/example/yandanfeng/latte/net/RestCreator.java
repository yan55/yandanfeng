package com.example.yandanfeng.latte.net;

import com.example.yandanfeng.latte.app.ConfigType;
import com.example.yandanfeng.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.yandanfeng.latte.net.RestCreator.RetrofitHolder.RETROFIT_CLIENT;


/**
 * Created by Administrator on 2017-11-08
 */

public class RestCreator {

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public  static  WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }


    public static final class RetrofitHolder {

        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());

        public static final Retrofit RETROFIT_CLIENT = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient
                .Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {

        private static final RestService REST_SERVICE
                = RETROFIT_CLIENT.create(RestService.class);

    }


}
