package com.koabs.common.logging;

/**
 * Log 工厂类可以替换Log 的实现类来使用不同的日志框架
 * @author koabs
 *
 */
public class LogFactory {
	private static Log log;
	static {
		log = new LogImpl();
	}
	
	
	private LogFactory() {
		super();
		// 禁止实例化
	}


	public static Log getInstance(){
		if(log == null){
			log = new LogImpl();
		}
		return log;
	}
}
