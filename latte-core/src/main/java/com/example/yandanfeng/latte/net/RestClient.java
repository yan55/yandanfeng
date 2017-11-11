package com.example.yandanfeng.latte.net;

import android.content.Context;

import com.example.yandanfeng.latte.net.callback.IError;
import com.example.yandanfeng.latte.net.callback.IFailure;
import com.example.yandanfeng.latte.net.callback.IRequest;
import com.example.yandanfeng.latte.net.callback.ISuccess;
import com.example.yandanfeng.latte.net.callback.RequestCallback;
import com.example.yandanfeng.latte.net.download.DownloadHandler;
import com.example.yandanfeng.latte.ui.LatteLoader;
import com.example.yandanfeng.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017-11-08
 */

public final class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAWS = RestCreator.getParams();
    private final IRequest REQUEST;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;


    private final Context CONTEXT;


    public RestClient(String url,
                      Map<String, Object> paraws,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loader_style

    ) {
        URL = url;
        PARAWS.putAll(paraws);
        REQUEST = request;
        DOWNLOAD_DIR = downloadDir;
        EXTENSION = extension;
        NAME = name;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        BODY = body;
        FILE = file;
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

            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;

            case PUT:
                call = service.put(URL, PARAWS);
                break;

            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;

            case DELETE:
                call = service.delete(URL, PARAWS);
                break;
            //下载功能
            case UPLOAD:

                final RequestBody requestBody = RequestBody
                        .create(MediaType.parse(MultipartBody.FORM.toString())
                                , FILE);
                final MultipartBody.Part body = MultipartBody
                        .Part
                        .createFormData("file", FILE.getName(), requestBody);

                call = RestCreator.getRestService().upload(URL, body);

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
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAWS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {

        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAWS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }


    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }


    public final void download() {
        new DownloadHandler(
                URL,
                REQUEST,
                DOWNLOAD_DIR,
                EXTENSION,
                NAME,
                SUCCESS,
                FAILURE,
                ERROR).handlerDownload();

    }

}
