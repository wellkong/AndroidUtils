package com.willkong.androidutils;

import android.app.Application;

import com.willkong.androidutils.library.CrashHandlerUtil;
import com.willkong.androidutils.library.LogUtil;

/**
 * Created by Willkong
 * on 2016/7/7.
 */
public class AndroidUtilsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.setLog(false);
        //崩溃处理
        CrashHandlerUtil crashHandlerUtil = CrashHandlerUtil.getInstance();
        crashHandlerUtil.init(this);
        crashHandlerUtil.setCrashTip("很抱歉，程序出现异常，即将退出！");
    }
}
