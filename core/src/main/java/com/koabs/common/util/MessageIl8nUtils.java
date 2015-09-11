
package com.koabs.common.util;

import org.springframework.context.MessageSource;

/**   
* @Title: MessageIl8nUtil.java 
* @Package com.koabs.common.util 
* @Description: 主要用来实现错误消息的国际化
* @author koabs 
* @date Aug 26, 2015 11:29:03 PM 
* @version V1.0   
*/
public class MessageIl8nUtils {
	
	private static MessageSource messageSource;
	
    /**
     * 根据消息键和参数 获取消息
     * 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(String code, Object... args) {
        if (messageSource == null) {
            messageSource = SpringUtils.getBean(MessageSource.class);
        }
        return messageSource.getMessage(code, args, null);
    }
}
