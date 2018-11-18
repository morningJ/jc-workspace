package com.java.smart.pojo;

import com.java.smart.enums.ResultCode;

import java.io.Serializable;

public class ResultBean implements Serializable {
	private String code;
	
	private String msg;
	
	private Object obj;
	
	public ResultBean(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public ResultBean(String code,String msg, Object obj){
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	public ResultBean(String code, Object obj){
		this.code = code;
		if(code.equals(ResultCode.susscess.getCode())){
			this.msg = ResultCode.susscess.getMsg();
		}else {
			this.msg = ResultCode.failed.getMsg();
		}
		this.obj = obj;
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

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
