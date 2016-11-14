package com.wenhao.netshop.utils;

/**
 * Created by lw on 2016/11/14.
 */
public class StringUtil {

    public static String toUpperCaseFirstOne(String string) {
        if (Character.isUpperCase(string.charAt(0)))
            return string;
        else
            return (new StringBuilder()).append(Character.toUpperCase(string.charAt(0)))
                    .append(string.substring(1)).toString();
    }
}
