package com.java.smart.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StaticInfo {
	public static Map<String,String> loginMap = new ConcurrentHashMap<String, String>();
	
	/**
	 * 用户登录，设置用户登录token
	 * @param id
	 * @param token
	 */
	public static void setLoginMap(String id, String token){
		loginMap.put(id, token);
	}
	
	/**
	 * 用户退出，删除用户登录token
	 * @param id
	 */
	public static void removeUserToken(String id){
		if(loginMap.containsKey(id)){
			loginMap.remove(id);
		}
	}
	
	public static String veriftToken(String id){
		if(loginMap.containsKey(id)){
			return loginMap.get(id);
		}
		return null;
	}
}
