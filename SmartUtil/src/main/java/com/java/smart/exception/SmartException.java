package com.java.smart.exception;

public class SmartException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	private String msg;
	
	public SmartException(){
		super();
	}
	
	public SmartException(String code, String msg){
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
