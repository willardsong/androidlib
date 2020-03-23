package com.infrastructure.upgrade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 *
 * @author zhwp
 * @date 2018/5/10
 */

public class InstallApkUtil {
	
	private static boolean stringIsNull(String str) {
		if (str != null && !"null".equals(str) && !TextUtils.isEmpty(str)) {
			return false;
		}
		
		return true;
	}
	
	public static void installApk(Context context, UpgradePackageInfo upgradePackageInfo, String filePath) {
		if (upgradePackageInfo == null || stringIsNull(upgradePackageInfo.getPackageName())) {
			return;
		}
		File apkFile = new File(filePath, upgradePackageInfo.getName());
		if (!apkFile.exists()) {
			return;
		}

		Intent intent = new Intent(Intent.ACTION_VIEW);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 对目标应用临时授权该Uri所代表的文件
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri contentUri = FileProvider.getUriForFile(context, upgradePackageInfo.getPackageName() + ".fileProvider", apkFile);
			intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
		} else {
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(Uri.parse("file://" + apkFile.toString()), "application/vnd.android.package-archive");
		}
		
		if (context != null && context.getPackageManager() != null && context.getPackageManager().queryIntentActivities(intent, 0) != null) {
			if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
				context.startActivity(intent);
				android.os.Process.killProcess(android.os.Process.myPid());// 如果不加，最后不会提示完成、打开。
			}
		}
		
	}
}
