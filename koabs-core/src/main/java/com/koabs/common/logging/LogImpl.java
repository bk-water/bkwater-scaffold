package com.koabs.common.logging;

/**
 * 日志实现类
 * @author kevin1
 *
 */
public class LogImpl implements Log{

	@Override
	public boolean isDebugEnabled() {
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		return false;
	}

	@Override
	public void error(String s, Throwable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String s) {
		// TODO Auto-generated method stub
		
	}

}
