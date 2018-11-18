package com.java.smart.util;

import java.util.*;

public class Constants {

	/**
	 * 登录Token设置
	 */
	public static final String COOKIE_KEY = "cookie_token";
	public static final String COOKIE_DOMAIN = "cookie_domain";
	public static final int TOKEN_REFRESH_TIME = 60;

	public static class Results {

		public static final String code = "code";

		public static final String msg = "msg";

		public static final String result = "result";

	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}


	public static void main(String[] args) {
		System.err.println(getUUID());
	}
}
