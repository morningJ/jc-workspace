package com.java.smart.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.java.smart.annotation.Token;
import com.java.smart.services.login.LoginService;
import com.java.smart.util.Constants.Results;

public class TokenInterceptor extends HandlerInterceptorAdapter{
	
	@Resource
	private LoginService loginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String userid = "";
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
			if(null !=annotation && !annotation.createKey()){
				String token = (String) request.getSession().getAttribute("token");
				String id = (String) request.getSession().getAttribute("id");
				userid = id;

				if((null != id && !"".equals(id)) &&  (null != token && !"".equals(token))){
					boolean result = loginService.veriftToken(id, token);
					if(!result){
						response.sendRedirect(request.getContextPath() + "/login");
						return false;
					}

					//token验证成功后更新session时间
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(10*60);
            	}else{
            		response.sendRedirect(request.getContextPath() + "/login");
            		
            		return false;
            	}
            }else if(null == annotation){
				//更新session时间,不校验结果
				String token = (String) request.getSession().getAttribute("token");
				String id = (String) request.getSession().getAttribute("id");
				userid = id;

				if((null != id && !"".equals(id)) &&  (null != token && !"".equals(token))) {
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(10 * 60);
				}
            }
            request.setAttribute("id", userid);
			
		    return super.preHandle(request, response, handler);
        } else {
            return super.preHandle(request, response, handler);
		
        }
	}
	
	/**
	 * 程序处理请求之后
	 * @Description 建立并传递token
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}
	
	
	/**
	 * 所有请求处理完成之后被调用的(如视图呈现之后).
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,  
        HttpServletResponse response,Object handler,Exception ex)throws Exception{  
    }
	
	private boolean errorLogin(HttpServletResponse response, String errorCode, String errorMsg){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Results.code, errorCode);
		map.put(Results.msg, errorMsg);
		response.setStatus(608);
//		ResponseUtils.renderJson(response, JsonUtil.buildNormalBinder().toJson(map));
		return false;
	}
	
}
