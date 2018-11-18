package com.java.smart.services.login;

import com.java.smart.enums.ResultCode;
import com.java.smart.exception.SmartException;
import com.java.smart.mybatis.mapper.LoginMapper;
import com.java.smart.mybatis.mapper.UserMapper;
import com.java.smart.mybatis.model.User;
import com.java.smart.pojo.LoginReq;
import com.java.smart.util.StaticInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {
	
	private static Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Resource
	UserMapper userMapper;

	@Resource
	LoginMapper loginMapper;
	
	public User login(LoginReq req) throws SmartException{
		if(null == req.getId() || "".equals(req.getId())){
			throw new SmartException(ResultCode.LoginFailed.getCode(),ResultCode.LoginFailed.getMsg());
		}

		User user = loginMapper.login(req.getId(),req.getPassword());

		if(null == user){
			logger.debug("未找到用户:"+ req.getId() +" 的用户信息");
			throw new SmartException(ResultCode.LoginFailed.getCode(),ResultCode.LoginFailed.getMsg());
		}else{
				//生成一个token存入redis中 --- 暂时为做，先放入内存map中
				//登录后生成一个token放入内存中供登录检查
				StaticInfo.setLoginMap(req.getId(), req.getToken());
		}
		
		
		return user;
	}
	
	
	/**
	 * 校验用户登录状态
	 * @param id
	 * @param token
	 * @return
	 */
	public boolean veriftToken(String id,String token){
		
		String userToken = StaticInfo.veriftToken(id);
		
		if(null != userToken){
			if(userToken.equals(token)){
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * 退出登录
	 * @param id
	 * @return
	 */
	public boolean loginOut(String id){
		
		StaticInfo.removeUserToken(id);
		
		return true;
	}
	
}
