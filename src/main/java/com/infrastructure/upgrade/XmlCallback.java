package com.infrastructure.upgrade;


import com.lzy.okgo.callback.AbsCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;


public abstract class XmlCallback<T> extends AbsCallback<List<T>> {


    public XmlCallback() {

    }

    @Override
    public List<T> convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        InputStream xmlIn = body.byteStream();
        List<UpgradePackageInfo> upgradeList = null;
        try {
            upgradeList = PullXmlService.getUpgradePackageInfoList(xmlIn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (xmlIn != null) {
                try {
                    xmlIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        response.close();
        return (List<T>) upgradeList;
    }
}