//package com.infrastructure.net.retrofic;
//
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * @author weijingsong
// * @date 2020/3/10
// */
//public class RetrofitManager {
//    private static String mBaseUrl = "";
//    private static Retrofit mRetrofit;
//
//    public static Retrofit getInstance() {
//        if (null == mRetrofit) {
//            synchronized (RetrofitManager.class) {
//                if (null == mRetrofit) {
//                    new RetrofitManager();
//                    return mRetrofit;
//                }
//            }
//        }
//        return mRetrofit;
//    }
//
//    private RetrofitManager() {
//        initHttpBase();
//    }
//
//    private void initHttpBase() {
////        OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .retryOnConnectionFailure(false)
////                .connectTimeout(20, TimeUnit.SECONDS)
////                .readTimeout(20, TimeUnit.SECONDS)
////                .writeTimeout(20, TimeUnit.SECONDS)
////                .build();
////
////        mRetrofit = new Retrofit.Builder()
////                .client(okHttpClient)
////                .baseUrl(mBaseUrl)
////                .addConverterFactory(GsonConverterFactory.create())
////                .callbackExecutor(Executors.newSingleThreadExecutor())
////                .build();
//    }
//
//    private void initBaseUrl(String baseUrl) {
//        mBaseUrl = baseUrl;
//    }
//}
