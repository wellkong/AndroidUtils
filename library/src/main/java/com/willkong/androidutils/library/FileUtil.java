package com.willkong.androidutils.library;

import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Willkong on 2016/9/7
 * 文件计算工具类
 */
public class FileUtil {

    private static FileUtil inst;

    public static FileUtil getInstance() {
        if (inst == null) {
            inst = new FileUtil();
        }
        return inst;
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     */
    public long getFolderSize(File file) {
        long size = 0;
        File[] fileList = file.listFiles();
        for (File aFileList : fileList) {
            if (aFileList.isDirectory()) {
                size = size + getFolderSize(aFileList);
            } else {
                size = size + aFileList.length();
            }
        }
        return size;
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(long size) {

        long kiloByte = size / 1024;
//        if (kiloByte < 1) {
//            return size + "Byte";
//        }

        long megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Long.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        long gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Long.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        long teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Long.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }


    private static final int GB = 1024 * 1024 * 1024;
    private static final int MB = 1024 * 1024;
    private static final int KB = 1024;
    private static final int BYTE2GB = 1;
    private static final int BYTE2MB = 2;
    private static final int BYTE2KB = 3;

    public static String getFileSize(long size, int type) {
        DecimalFormat df = new DecimalFormat("0.00");
        String resultSize = "";
        switch (type) {
            case BYTE2GB:
                resultSize = df.format(size / (float) GB).replace(",", ".") + "GB";
                break;
            case BYTE2MB:
                resultSize = df.format(size / (float) MB).replace(",", ".") + "MB";
                break;
            case BYTE2KB:
                resultSize = df.format(size / (float) KB).replace(",", ".") + "KB";
                break;
            default:
                break;
        }
        return resultSize;
    }
}