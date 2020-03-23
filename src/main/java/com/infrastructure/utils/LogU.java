package com.infrastructure.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author weijingsong
 * @date 2020/3/12
 */
public class LogU {
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int WTF = 6;
    private static final int TI = 0;
    private static String className;
    private static String methodName;
    private static int lineNumber;
    private static int logLevel = 0;
    private static boolean isDebug = true;

    public LogU() {
    }

    private static boolean isDebuggable() {
        return isDebug;
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }

    public static void setLogLevel(int level) {
        logLevel = level;
    }

    private static String createLog(String log) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void v(String... args) {
        if (isDebuggable()) {
            int len = args.length;
            if (logLevel <= 1) {
                getMethodNames((new Throwable()).getStackTrace());
                switch(len) {
                    case 1:
                        Log.v(className, createLog(args[0]));
                        break;
                    default:
                        String tag = TextUtils.isEmpty(args[0]) ? className : args[0];

                        for(int i = 1; i < args.length; ++i) {
                            Log.v(tag, createLog(args[i]));
                        }
                }
            }

        }
    }

    public static void d(String... args) {
        if (isDebuggable()) {
            int len = args.length;
            if (logLevel <= 2) {
                getMethodNames((new Throwable()).getStackTrace());
                switch(len) {
                    case 1:
                        Log.d(className, createLog(args[0]));
                        break;
                    default:
                        String tag = TextUtils.isEmpty(args[0]) ? className : args[0];

                        for(int i = 1; i < args.length; ++i) {
                            Log.d(tag, createLog(args[i]));
                        }
                }
            }

        }
    }

    public static void i(String... args) {
        if (isDebuggable()) {
            int len = args.length;
            if (logLevel <= 3) {
                getMethodNames((new Throwable()).getStackTrace());
                switch(len) {
                    case 1:
                        Log.i(className, createLog(args[0]));
                        break;
                    default:
                        String tag = TextUtils.isEmpty(args[0]) ? className : args[0];

                        for(int i = 1; i < args.length; ++i) {
                            Log.i(tag, createLog(args[i]));
                        }
                }
            }

        }
    }

    public static void w(String... args) {
        if (isDebuggable()) {
            int len = args.length;
            if (logLevel <= 4) {
                getMethodNames((new Throwable()).getStackTrace());
                switch(len) {
                    case 1:
                        Log.w(className, createLog(args[0]));
                        break;
                    default:
                        String tag = TextUtils.isEmpty(args[0]) ? className : args[0];

                        for(int i = 1; i < args.length; ++i) {
                            Log.w(tag, createLog(args[i]));
                        }
                }
            }

        }
    }

    public static void e(String... args) {
        if (isDebuggable()) {
            int len = args.length;
            if (logLevel <= 5) {
                getMethodNames((new Throwable()).getStackTrace());
                switch(len) {
                    case 1:
                        Log.e(className, createLog(args[0]));
                        break;
                    default:
                        String tag = TextUtils.isEmpty(args[0]) ? className : args[0];

                        for(int i = 1; i < args.length; ++i) {
                            Log.e(tag, createLog(args[i]));
                        }
                }
            }

        }
    }

    public static void wtf(String message) {
        if (isDebuggable()) {
            if (logLevel <= 6) {
                getMethodNames((new Throwable()).getStackTrace());
                Log.wtf(className, createLog(message));
            }

        }
    }

    public static void ti(String message) {
        if (isDebuggable()) {
            if (logLevel <= 0) {
                StackTraceElement[] sElements = (new Throwable()).getStackTrace();
                int count = sElements.length;
                if (count > 1) {
                    for(int i = 1; i < count; ++i) {
                        StackTraceElement element = sElements[i];
                        StringBuilder buffer = new StringBuilder();
                        buffer.append(element.getMethodName());
                        buffer.append(">index:").append(i).append(">(").append(element.getClassName()).append(":").append(element.getLineNumber()).append(")");
                        buffer.append(message);
                        Log.d(element.getClassName(), buffer.toString());
                    }
                }
            }

        }
    }
}
