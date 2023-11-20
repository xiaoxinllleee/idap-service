package org.cmms.common.util;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 简单错误信息
 * 
 * @author John
 * 
 */
public class SimpleException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Exception e;

	public SimpleException() {
		this.e = new Exception();
	}

	public SimpleException(Exception e) {
		this.e = e;
	}

	@Override
	public String getMessage() {
		String errorMsg=null;
		if(e.getMessage()!=null){
			errorMsg=e.getMessage();
		}else if(e.getCause()!=null && e.getCause().getMessage()!=null){
			errorMsg=e.getMessage();
		}
		return "简单错误日志:"+errorMsg;
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return new StackTraceElement[0];
	}

	public void printStackTrace() {
		
	}

	public void printStackTrace(PrintStream s) {
		s.append(getMessage());
	}

	public void printStackTrace(PrintWriter s) {
		s.append(getMessage());
	}
}
