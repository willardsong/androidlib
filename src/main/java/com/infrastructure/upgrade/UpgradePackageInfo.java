package com.infrastructure.upgrade;

import java.io.Serializable;

public class UpgradePackageInfo implements Serializable {
    /*
<root>
    <file>
        <name>3QStory.apk</name>
        <packageName>com.children.childrensapp</packageName>
        <fileType>apk</fileType>
        <length>22757376</length>
        <versionCode>31</versionCode>
        <versionName>V3.1.0_20180210_10272</versionName>
        <Md5Sum>8ddb342f2da5408402d7568af21e29f9</Md5Sum>
        <updateTime>2018-02-10</updateTime>
        <imageUrl>http://download/3QStory.png</imageUrl>
    <downloadUrl>http://download/3QStory.apk</downloadUrl>
    <description>1、修复锤子手机UI不适配的bug。|2、修复一些绘本录制相关的bug。|3、更新一套全新的UI。</description>
    <remind>温馨提示：如升级失败，请卸载当前应用，并到应用市场上下载安装，您的支持将是我们最大的动力，感谢您对3Q故事的支持！</remind>
    <updateMode>0</updateMode>
    </file>
    </root>
     */

    private String name;        // xml 中定义的apk 名称
    private String packageName; // xml 中定义的 packageName
    private String fileType;    // xml 中定义的 apk 文件类型
    private long length;        // xml 中定义的 apk 文件大小
    private int versionCode;    // xml 中定义的 apk versionCode
    private String versionName; // xml 中定义的 apk versionName
    private String Md5Sum;      // xml 中定义的 apk 的md5sum值
    private String updateTime;  // xml 中定义的 apk 更新时间
    private String imageUrl;    // xml 中定义的 apk 图片地址
    private String downloadUrl; // xml 中定义的 apk 下载地址
    private String description; // xml 中定义的 apk 信息描述
    private String remind;      // xml 中定义的 apk 提示信息
    private int updateMode;     // xml 中定义的 apk 升级更新方式

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getMd5Sum() {
        return Md5Sum;
    }

    public void setMd5Sum(String md5Sum) {
        Md5Sum = md5Sum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public int getUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(int updateMode) {
        this.updateMode = updateMode;
    }

    @Override
    public String toString() {
        String result = "(name:" + name + ","
                        + "packageName:" + packageName + ","
                        + "fileType:" + fileType + ","
                        + "mLength:" + length + ","
                        + "versionCode:" + versionCode + ","
                        + "versionName:" + versionName + ","
                        + "Md5Sum:" + Md5Sum + ","
                        + "updateTime:" + updateTime + ","
                        + "imageUrl:" + imageUrl + ","
                        + "downloadUrl:" + downloadUrl + ","
                        + "description:" + description + ","
                        + "remind:" + remind + ","
                        + "updateMode:" + updateMode + ")";
        return result;
    }
}
