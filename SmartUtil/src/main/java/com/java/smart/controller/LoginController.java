package com.java.smart.controller;

import com.alibaba.fastjson.JSON;
import com.java.smart.annotation.Token;
import com.java.smart.enums.ResultCode;
import com.java.smart.exception.SmartException;
import com.java.smart.pojo.LoginReq;
import com.java.smart.mybatis.model.User;
import com.java.smart.services.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}

	@Token(createKey = true)
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody
	String login(HttpServletRequest request, @RequestBody LoginReq req){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			String sessionId = request.getSession().getId();
			req.setToken(sessionId);

			User user = loginService.login(req);
			
			HttpSession session = request.getSession();
			session.setAttribute("id", user.getId());
			session.setAttribute("token", sessionId);
			session.setMaxInactiveInterval(10*60);


			map.put("code", ResultCode.susscess.getCode());
			map.put("msg",ResultCode.susscess.getMsg());

		}catch(SmartException e){
			logger.debug(e.getCode() +" : "+e.getMsg());
			map.put("code", e.getCode());
			map.put("msg", e.getMsg());
		}
		return JSON.toJSONString(map);
	}

}
