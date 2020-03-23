package com.infrastructure.upgrade;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class PullXmlService {

	private static final String ROOT_TAG			= "file";
	private static final String NAME_TAG			= "name";
	private static final String PACKAGE_NAME_TAG	= "packageName";
	private static final String FILE_TYPE_TAG		= "fileType";
	private static final String LENGTH_TAG			= "length";
	private static final String VERSION_CODE_TAG	= "versionCode";
	private static final String VERSION_NAME_TAG	= "versionName";
	private static final String MD5_SUM_TAG			= "Md5Sum";
	private static final String UPDATE_TIME_TAG		= "updateTime";
	private static final String IMAGE_URL_TAG		= "imageUrl";
	private static final String DOWNLOAD_URL_TAG	= "downloadUrl";
	private static final String DESCRIPTION_TAG		= "description";
	private static final String REMIND_TAG			= "remind";
	private static final String UPDATE_MODE_TAG		= "updateMode";
    /**
     * ------------------------使用PULL解析XML-----------------------
     * @param inStream
     * @return
     * @throws Exception
     */
    public static List<UpgradePackageInfo> getUpgradePackageInfoList(InputStream inStream)
            throws Exception {
        UpgradePackageInfo upgradePackageInfo = null;
        List<UpgradePackageInfo> upgradePackageInfoList = null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(inStream, "UTF-8");
        int event = pullParser.getEventType();// 觸發第一個事件
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    upgradePackageInfoList = new ArrayList<UpgradePackageInfo>();
                    break;
                case XmlPullParser.START_TAG:
                    if (ROOT_TAG.equals(pullParser.getName())) {
                        upgradePackageInfo = new UpgradePackageInfo();
                    }
                    if (upgradePackageInfo != null) {
                        if (NAME_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setName(name);
                        } else if (PACKAGE_NAME_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setPackageName(name);
                        } else if (FILE_TYPE_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setFileType(name);
                        } else if (LENGTH_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setLength(Long.parseLong(name));
                        } else if (VERSION_CODE_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setVersionCode(Integer.parseInt(name));
                        } else if (VERSION_NAME_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setVersionName(name);
                        } else if (MD5_SUM_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setMd5Sum(name);
                        } else if (UPDATE_TIME_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setUpdateTime(name);
                        } else if (IMAGE_URL_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setImageUrl(name);
                        } else if (DOWNLOAD_URL_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setDownloadUrl(name);
                        } else if (DESCRIPTION_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setDescription(name);
                        } else if(REMIND_TAG.equals(pullParser.getName())){
                            String name = pullParser.nextText();
                            upgradePackageInfo.setRemind(name);
                        }else if (UPDATE_MODE_TAG.equals(pullParser.getName())) {
                            String name = pullParser.nextText();
                            upgradePackageInfo.setUpdateMode(Integer.parseInt(name));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (ROOT_TAG.equals(pullParser.getName())) {
                        upgradePackageInfoList.add(upgradePackageInfo);
                        upgradePackageInfo = null;
                    }
                    break;
            }
            event = pullParser.next();
        }
        return upgradePackageInfoList;
    }
}
