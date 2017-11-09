package com.example.yandanfeng.latte.net.callback;

import android.os.Handler;

import com.example.yandanfeng.latte.ui.LatteLoader;
import com.example.yandanfeng.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017-11-08
 */

public class RequestCallback implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYPE;

    //handler 最好可以加static这样可以避免内存泄漏
    private static final Handler HANDLER = new Handler();


    public RequestCallback(IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           LoaderStyle loader_stype) {

        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYPE = loader_stype;

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        //请求成功了
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {

                ERROR.onError(response.code(), response.message());

            }

        }
        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();

    }

    private void stopLoading() {
        if (LOADER_STYPE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {

                    LatteLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
