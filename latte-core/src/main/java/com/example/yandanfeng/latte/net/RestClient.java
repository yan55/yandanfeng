package com.example.yandanfeng.latte.net;

import android.content.Context;

import com.example.yandanfeng.latte.net.callback.IError;
import com.example.yandanfeng.latte.net.callback.IFailure;
import com.example.yandanfeng.latte.net.callback.IRequest;
import com.example.yandanfeng.latte.net.callback.ISuccess;
import com.example.yandanfeng.latte.net.callback.RequestCallback;
import com.example.yandanfeng.latte.ui.LatteLoader;
import com.example.yandanfeng.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017-11-08
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAWS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    public RestClient(String url,
                      Map<String, Object> paraws,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      Context context,
                      LoaderStyle loader_style
    ) {
        URL = url;
        PARAWS.putAll(paraws);
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        BODY = body;
        CONTEXT = context;
        LOADER_STYLE = loader_style;
    }

    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequeststart();

        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);

        }
        switch (method) {
            case GET:

                call = service.get(URL, PARAWS);

                break;
            case POST:
                call = service.post(URL, PARAWS);
                break;
            case PUT:
                call = service.put(URL, PARAWS);
                break;
            case DELETE:
                call = service.delete(URL, PARAWS);
                break;

            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());

        }

    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
