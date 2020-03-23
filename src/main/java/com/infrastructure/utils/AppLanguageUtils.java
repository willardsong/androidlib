package com.infrastructure.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;


import java.util.HashMap;
import java.util.Locale;

/**
 * @author weijingsong
 * @date 2020/2/21
 */
public class AppLanguageUtils implements Constant {

    private static HashMap<String, Locale> mAllLanguages = new HashMap<String, Locale>(7) {
        {
            put(ENGLISH, Locale.ENGLISH);
            put(SIMPLIFIED_CHINESE, Locale.SIMPLIFIED_CHINESE);
            put(TRADITIONAL_CHINESE, Locale.SIMPLIFIED_CHINESE);
            put(CHINESE, Locale.SIMPLIFIED_CHINESE);
            put(GERMAN, Locale.GERMANY);
            put(HINDI, new Locale(HINDI, "IN"));
            put(ITALIAN, Locale.ITALY);
            put(CZECH, new Locale(CZECH, "cs"));
        }
    };

    public static void changeAppLanguage(Context context, String newLanguage) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getLocaleByLanguage(newLanguage);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        //save language
    }

    public static String getLanguage(Context context) {
        String languageDefault = getDefaultLocale(context).getLanguage();
        //get save language
        return languageDefault;
    }

    public static boolean isSupportLanguage(String language) {
        return mAllLanguages.containsKey(language);
    }

    public static String getSupportLanguage(String language) {
        if (isSupportLanguage(language)) {
            return language;
        } else {
            return ENGLISH;
        }
    }

    public static Locale getDefaultLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            return context.getResources().getConfiguration().locale;
        }
    }

    public static Locale getLocaleByLanguage(String language) {
        if (isSupportLanguage(language)) {
            return mAllLanguages.get(language);
        } else {
            return Locale.ENGLISH;
        }
    }


}
