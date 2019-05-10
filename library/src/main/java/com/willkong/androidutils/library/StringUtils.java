package com.willkong.androidutils.library;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Willkong
 * on 2016/3/22.
 */
public class StringUtils {

    /**
     * 获取字符串中出现某个字符的次数
     *
     * @param str
     * @param key
     * @return
     */
    public static int getKeyTime(String str, String key) {
        int index = 0; //定义变量。记录每一次找到的key的位置。
        int count = 0; //定义变量，记录出现的次数。
        if (str != null && !TextUtils.isEmpty(str)) {
            //定义循环。只要索引到的位置不是-1，继续查找。
            while ((index = str.indexOf(key, index)) != -1) {
                //每循环一次，就要明确下一次查找的起始位置。
                index = index + key.length();
                //每查找一次，count自增。
                count++;
            }
        }
        return count;
    }

    /**
     * 数字字符串转换格式化
     * 保留两位小数
     *
     * @param s
     * @return
     */
    public static String stringFormat(String s) {
        String p = "";//format 返回的是字符串
        try {
            float f = Float.parseFloat(s);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            p = decimalFormat.format(f);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * 把String转化为float
     *
     * @param number
     * @param defaultValue
     * @return
     */
    public static float stringToFloat(String number, float defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    /**
     * 把String转化为double
     *
     * @param number
     * @param defaultValue
     * @return
     */
    public static double stringToDouble(String number, double defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    /**
     * 把String转化为int
     *
     * @param number
     * @param defaultValue
     * @return
     */
    public static int stringToInt(String number, int defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 替换换行符和空格
     * @param src
     * @return
     */
    public static String replaceBlank(String src) {
        String dest = "";
        if (src != null) {
            Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
            Matcher matcher = pattern.matcher(src);
            dest = matcher.replaceAll("");
        }
        return dest;
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }
}
