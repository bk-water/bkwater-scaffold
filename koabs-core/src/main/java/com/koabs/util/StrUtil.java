package com.koabs.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Author: koabs
 * 2020/8/26.
 */
public class StrUtil {

    public static String  substring(String str,Integer start,Integer end) {
        return str.substring(start,end);
    }

    public static Boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

}
