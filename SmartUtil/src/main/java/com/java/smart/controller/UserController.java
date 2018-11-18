package com.java.smart.controller;

import com.github.pagehelper.PageInfo;
import com.java.smart.annotation.Token;
import com.java.smart.enums.ResultCode;
import com.java.smart.exception.SmartException;
import com.java.smart.pojo.UserRes;
import com.java.smart.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Token
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public ModelMap login(HttpServletRequest request){
		ModelMap map = new ModelMap();

		String userId = (String) request.getAttribute("id");

		try {
			UserRes user = userService.getUserInfoById(userId);

			List<UserRes> userList = userService.getAllUserList();

			map.put("code", ResultCode.susscess.getCode());
			map.put("msg", ResultCode.susscess.getMsg());
			map.put("queryParam",user);
			map.put("userList", new PageInfo<UserRes>(userList));
		}catch (SmartException e){
			logger.debug(e.getCode() +" : "+e.getMsg());
			map.put("code", e.getCode());
			map.put("msg", e.getMsg());
		}

		return map;
	}
}
