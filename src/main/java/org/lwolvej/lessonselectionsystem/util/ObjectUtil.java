package org.lwolvej.lessonselectionsystem.util;

import com.google.common.base.Strings;

import java.util.regex.Pattern;

/**
 * 实用工具类
 *
 * @author LwolveJ
 */
public class ObjectUtil {

    //string向long的转化
    public static Long StringToLong(String number) {
        //判断是否为空字符串，如果是将其转换为null
        String now = Strings.emptyToNull(number);
        if (now != null) {
            //判断是否都为数字，否则返回null
            if (ifAllNumber(now)) {
                return Long.parseLong(now);
            }
        }
        return null;
    }

    //string向int的转换
    public static Integer StringToInteger(String number) {
        String now = Strings.emptyToNull(number);
        if (now != null) {
            if (ifAllNumber(number)) {
                return Integer.parseInt(now);
            }
        }
        return null;
    }

    //字符串检查工具：检查字符串是否为空，并且去除前后空格
    public static String stringCheck(String arr) {
        String result = Strings.emptyToNull(arr);
        return result != null ? result.trim() : null;
    }

    //正则匹配是否一个字符串中都为数字
    public static Boolean ifAllNumber(String arr) {
        String reg = "^[0-9]*$";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(arr).matches();
    }
}
