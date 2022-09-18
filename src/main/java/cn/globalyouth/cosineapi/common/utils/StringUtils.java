package cn.globalyouth.cosineapi.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuyufeng
 * 字符串工具类
 */
public class StringUtils {

    public static boolean notEmpty(String s) {
        return null != s && !"".equals(s);
    }

    /**
     * timestamp 转 "12月21日" 格式
     *
     * @param date timestamp
     * @return String
     */
    public static String getDateStr(Date date) {
        DateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(date);
    }

    /**
     * timestamp 转 "18:32" 格式
     *
     * @param date timestamp
     * @return String
     */
    public static String getTimeStr(Date date) {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    /**
     * timestamp 转 "2020年02月27日" 格式
     *
     * @param date timestamp
     * @return String
     */
    public static String getFullDateStr(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    /**
     * date 转 2021-03-02 格式
     *
     * @param date 时间
     * @return String
     */
    public static String getFullDateStr2(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 压缩字符串长度
     *
     * @param string 源字符串
     * @param length 最大长度
     * @return String
     */
    public static String subString(String string, int length) {
        if (null == string) {
            return "";
        }
        if (string.length() <= length) {
            return string;
        }
        return string.substring(0, 17) + "...";
    }
}
