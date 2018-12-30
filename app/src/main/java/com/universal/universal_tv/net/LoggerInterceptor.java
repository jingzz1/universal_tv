package com.universal.universal_tv.net;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by Administrator - PC on 2017/12/27.
 */

public class LoggerInterceptor implements Interceptor {
    public static final String TAG = "OkHttp3";
    private String tag;
    private boolean showResponse;

    public LoggerInterceptor(String tag, boolean showResponse) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        this.showResponse = showResponse;
        this.tag = tag;
    }

    public LoggerInterceptor(boolean showResponse) {
        this(TAG, showResponse);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        return logForResponse(response);
    }

    private Response logForResponse(Response response) {
        try {
            if (showResponse) {
                //===>response log
                Log.e(tag, "========response'log=======");
                Response.Builder builder = response.newBuilder();
                Response clone = builder.build();
                Request request = clone.request();
                Log.e(tag, "url : " + URLDecoder.decode(request.url().toString(), "UTF-8"));
                String method = request.method();
                if (method.equals("POST")) {
                    RequestBody requestBody = request.body();
                    if(requestBody != null){
                        Buffer buffer = new Buffer();
                        requestBody.writeTo(buffer);
                        String postParams = buffer.readUtf8();
                        if (!TextUtils.isEmpty(postParams) && postParams.indexOf("filename") == -1)
                            Log.e(tag, "POSTParams : { " + URLDecoder.decode(postParams.replace("&", " , "), "UTF-8") + " }");
                    }
                }
                Log.e(tag, "code : " + clone.code());
                Log.e(tag, "protocol : " + clone.protocol());
                if (!TextUtils.isEmpty(clone.message()))
                    Log.e(tag, "message : " + clone.message());

                ResponseBody body = clone.body();
                if (body != null) {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null) {
                        Log.e(tag, "responseBody's contentType : " + mediaType.toString());
                        if (isText(mediaType)) {
                            String resp = body.string();
                            Log.e(tag, "------------------------------------------------------------");
                            Log.e(tag, "responseBody's content : " + resp);
                            Log.e(tag, "------------------------------------------------------------");
                            body = ResponseBody.create(mediaType, resp);
                            return response.newBuilder().body(body).build();
                        } else {
                            Log.e(tag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                        }
                    }

                }
                Log.e(tag, "========response'log=======end");
            }


        } catch (Exception e) {
//            e.printStackTrace();
            Log.e(tag, e.toString());
        }

        return response;
    }

    private void logForRequest(Request request) {
        try {
            if (showResponse) {
                String url = request.url().toString();
                url = URLDecoder.decode(url, "UTF-8");
                Headers headers = request.headers();
                String method = request.method();
                RequestBody requestBody = request.body();
                Log.e(tag, "========request'log=======");
                Log.e(tag, "method : " + method);
                Log.e(tag, "url : " + url);
                if(requestBody != null){
                    //打印post请求参数
                    if (method.equals("POST")) {
                        Buffer buffer = new Buffer();
                        requestBody.writeTo(buffer);
                        String postParams = buffer.readUtf8();

                        if (!TextUtils.isEmpty(postParams) && postParams.indexOf("filename") == -1)
                            Log.e(tag, "POSTParams : { " + URLDecoder.decode(postParams.replace("&", " , "), "UTF-8") + " }");

                    }
                }
                if (headers != null && headers.size() > 0) {
                    Log.e(tag, "headers : " + headers.toString());
                }
                if (requestBody != null) {
                    MediaType mediaType = requestBody.contentType();
                    if (mediaType != null) {
                        Log.e(tag, "requestBody's contentType : " + mediaType.toString());
                        if (isText(mediaType)) {
                            Log.e(tag, "requestBody's content : " + bodyToString(request));
                        } else {
                            Log.e(tag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                        }
                    }
                }

                Log.e(tag, "========request'log=======end");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            Log.e(tag, e.toString());
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            Log.e(tag, e.toString());
            return "something error when show requestBody.";
        }
    }
}
