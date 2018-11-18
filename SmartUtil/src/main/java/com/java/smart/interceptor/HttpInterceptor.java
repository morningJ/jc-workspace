package com.java.smart.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.java.smart.util.Constants.Results;
import com.java.smart.util.StringUtil;

public class HttpInterceptor extends HandlerInterceptorAdapter {
	/**
     * 最后执行，可用于释放资源
     */
    public void afterCompletion(HttpServletRequest res,HttpServletResponse rep, Object arg2, Exception e) throws Exception {
    	
    }
    
    /**
     * 生成视图之前执行
     */
    public void postHandle(HttpServletRequest res, HttpServletResponse rep,
            Object handler, ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
    	if(modelAndView != null){
    	}
    	
    }
    
    /**
     * Action之前执行
     */
    public boolean preHandle(HttpServletRequest res, HttpServletResponse rep, Object handler) throws Exception {
    	String  url = res.getRequestURI();
	    if(url ==null || "".equals(url)) {
		    return false;
	    }

        return true;

    }
    
    
    
	
	public void showParaValues(HttpServletRequest req){  
		Map<String, Object> dataReq = new HashMap<String, Object>();
		Enumeration<String> paramNames=req.getParameterNames();
		while(paramNames.hasMoreElements()){
			 String paramName = (String) paramNames.nextElement();
			 String[] paramValues = req.getParameterValues(paramName);
			 if(paramValues==null || paramValues.length == 0){
				 dataReq.put(paramName, null);
			 }else if(paramValues.length == 1){
				 dataReq.put(paramName, paramValues[0]);
			 }else{
				 dataReq.put(paramName, paramValues);
			 }
		}
	}
}
