package com.infrastructure.upgrade;


import androidx.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.List;


public class UpgradeApi {

    public static final String TAG_CHECKUPGRADE = "checkUpgrade";
    public static final String TAG_DOWNLOAD = "download";

    /**
     * http get default
     * @param checkUrl
     * @param httpParams
     * @param onCheckUpgradeListener
     */
    public static void checkUpgrade(@NonNull String checkUrl, HttpParams httpParams, final OnCheckUpgradeListener onCheckUpgradeListener) {
        OkGo.get(checkUrl)
                .tag(TAG_CHECKUPGRADE)
                .params(httpParams)
                .execute(new XmlCallback() {
                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                        onCheckUpgradeListener.onStart();
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        List<UpgradePackageInfo> list = (List<UpgradePackageInfo>) response.body();
                        onCheckUpgradeListener.onCheckUpgradeCallback(false, list);
                    }

                    @Override
                    public void onSuccess(Response response) {
                        List<UpgradePackageInfo> list = (List<UpgradePackageInfo>) response.body();
                        onCheckUpgradeListener.onCheckUpgradeCallback(true, list);
                    }
                });
    }

    /**
     * http post
     * @param checkUrl
     * @param httpParams
     * @param onCheckUpgradeListener
     */
    public static void checkUpgradePost(@NonNull String checkUrl, HttpParams httpParams, final OnCheckUpgradeListener onCheckUpgradeListener) {
        OkGo.post(checkUrl)
                .tag(TAG_CHECKUPGRADE)
                .params(httpParams)
                .execute(new XmlCallback() {
                    @Override
                    public void onStart(Request request) {
                        super.onStart(request);
                        onCheckUpgradeListener.onStart();
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        List<UpgradePackageInfo> list = (List<UpgradePackageInfo>) response.body();
                        onCheckUpgradeListener.onCheckUpgradeCallback(false, list);
                    }

                    @Override
                    public void onSuccess(Response response) {
                        List<UpgradePackageInfo> list = (List<UpgradePackageInfo>) response.body();
                        onCheckUpgradeListener.onCheckUpgradeCallback(true, list);
                    }
                });
    }

    /**
     * http get
     * @param checkUrl
     * @param httpParams
     * @param xmlCallbackExpand, custom xml parse
     */
    public static void checkUpgradeExpand(@NonNull String checkUrl, HttpParams httpParams, final XmlCallbackExpand xmlCallbackExpand) {
        OkGo.get(checkUrl)
                .tag(TAG_CHECKUPGRADE)
                .params(httpParams)
                .execute(xmlCallbackExpand);
    }

    /**
     * http post
     * @param checkUrl
     * @param httpParams
     * @param xmlCallbackExpand
     */
    public static void checkUpgradeExpandPost(@NonNull String checkUrl, HttpParams httpParams, final XmlCallbackExpand xmlCallbackExpand) {
        OkGo.post(checkUrl)
                .tag(TAG_CHECKUPGRADE)
                .params(httpParams)
                .execute(xmlCallbackExpand);
    }

    /**
     * cancel checkupgrade call
     */
    public static void cancelCheckUpgradeRequest() {
        OkGo.getInstance().cancelTag(TAG_CHECKUPGRADE);
    }

    /**
     * cancel download call
     */
    public static void cancelDownloadApkRequest() {
        OkGo.getInstance().cancelTag(TAG_DOWNLOAD);
    }

    /**
     * http get
     * @param savePath
     * @param upgradePackageInfo
     * @param httpParams
     * @param listener
     */
    public static void downloadApk(@NonNull String savePath, @NonNull final UpgradePackageInfo upgradePackageInfo,
                                   HttpParams httpParams, final OnDownloadApkListener listener) {
        OkGo.<java.io.File>get(upgradePackageInfo.getDownloadUrl())
                .tag(TAG_DOWNLOAD)
                .params(httpParams)
                .execute(new FileCallback(savePath, upgradePackageInfo.getName()) {

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        listener.onStart();
                    }

                    @Override
                    public void onSuccess(Response<java.io.File> response) {
                        java.io.File file = response.body();
                        boolean checkMd5 = true;
                        if(upgradePackageInfo.getMd5Sum() != null) {
                            checkMd5 = upgradePackageInfo.getMd5Sum().equals(MD5Util.getFileMD5(file));
                        }
                        listener.onDownloadFinish(true, checkMd5, file);
                    }

                    @Override
                    public void onError(Response<java.io.File> response) {
                        super.onError(response);
                        listener.onDownloadFinish(false, false, null);
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        listener.onDownloadProgress(progress.fraction);
                    }

                });
    }

    /**
     * http post
     * @param savePath
     * @param upgradePackageInfo
     * @param httpParams
     * @param listener
     */
    public static void downloadApkPost(@NonNull String savePath, @NonNull final UpgradePackageInfo upgradePackageInfo,
                                       HttpParams httpParams, final OnDownloadApkListener listener) {
        OkGo.<java.io.File>post(upgradePackageInfo.getDownloadUrl())
                .tag(TAG_DOWNLOAD)
                .params(httpParams)
                .execute(new FileCallback(savePath, upgradePackageInfo.getName()) {

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        listener.onStart();
                    }

                    @Override
                    public void onSuccess(Response<java.io.File> response) {
                        java.io.File file = response.body();
                        boolean checkMd5 = upgradePackageInfo.getMd5Sum().equals(MD5Util.getFileMD5(file));
                        listener.onDownloadFinish(true, checkMd5, file);
                    }

                    @Override
                    public void onError(Response<java.io.File> response) {
                        super.onError(response);
                        listener.onDownloadFinish(false, false, null);
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        listener.onDownloadProgress(progress.fraction);
                    }

                });
    }

    public interface OnCheckUpgradeListener {
        void onStart();
        void onCheckUpgradeCallback(boolean isSuccess, List<UpgradePackageInfo> list);
    }

    public interface OnDownloadApkListener {
        void onStart();
        void onDownloadFinish(boolean success, boolean checkMd5, java.io.File file);
        void onDownloadProgress(float progress);
    }
}
