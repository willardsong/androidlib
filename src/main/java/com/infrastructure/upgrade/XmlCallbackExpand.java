package com.infrastructure.upgrade;



import androidx.annotation.NonNull;

import com.lzy.okgo.callback.AbsCallback;
import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;
import okhttp3.ResponseBody;


public abstract class XmlCallbackExpand<T> extends AbsCallback<T> {

    private Class<T> mClass;
    private String mAlias;

    public XmlCallbackExpand(@NonNull String alias, Class<T> clazz) {
        this.mAlias = alias;
        this.mClass = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        InputStream xmlIn = body.byteStream();
        Object data = null;
        try {
            XStream xstream = new XStream();
            if (this.mClass != null) {
                xstream.alias(mAlias, this.mClass);
                data = xstream.fromXML(xmlIn);
            }
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
        return (T) data;
    }
}