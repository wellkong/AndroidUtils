package com.willkong.androidutils.library;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Willkong
 * on 2016/3/22.
 */
public class SystemUtils {
    /**
     * 判断应用是否安装
     *
     * @param context
     * @param pkgName
     * @return
     */
    public static boolean checkAppInstalled(Context context, String pkgName) {
        if (pkgName == null || pkgName.isEmpty()) {
            return false;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;//true为安装了，false为未安装
        }
    }

    /**
     * 判断应用是否安装
     *
     * @param context
     * @param pkgName
     * @return
     */
    public static PackageInfo getPackageInfo(Context context, String pkgName) {
        if (pkgName == null || pkgName.isEmpty()) {
            return null;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 获取系统安装的所有应用
     *
     * @param context
     */
    public static List<PackageInfo> getSystemAllAppList(Context context) {
        PackageManager pm = context.getPackageManager();
        // Return a List of all packages that are installed on the device.
        return pm.getInstalledPackages(0);
    }

    /**
     * 获取系统安装的内置应用
     *
     * @param context
     */
    public static List<PackageInfo> getSystemInsertAppList(Context context) {
        List<PackageInfo> packages = getSystemAllAppList(context);
        List<PackageInfo> insertPackages = new ArrayList<>();
        for (PackageInfo packageInfo : packages) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                // 系统应用
                insertPackages.add(packageInfo);
            }
        }
        return insertPackages;
    }

    /**
     * 获取系统安装的非内置应用
     *
     * @param context
     */
    public static List<PackageInfo> getSystemOutsertAppList(Context context) {
        List<PackageInfo> packages = getSystemAllAppList(context);
        List<PackageInfo> outsertPackages = new ArrayList<>();
        for (PackageInfo packageInfo : packages) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                // 非系统应用
                outsertPackages.add(packageInfo);
            }
        }
        return outsertPackages;
    }
}
