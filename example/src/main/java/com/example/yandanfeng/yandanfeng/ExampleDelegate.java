package com.example.yandanfeng.yandanfeng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.yandanfeng.latte.delegate.LatteDelegate;
import com.example.yandanfeng.latte.net.RestClient;
import com.example.yandanfeng.latte.net.callback.IError;
import com.example.yandanfeng.latte.net.callback.IFailure;
import com.example.yandanfeng.latte.net.callback.ISuccess;

/**
 * Created by Administrator on 2017-11-08
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_exaple;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        testRestClient();

    }

    private void testRestClient() {

        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
