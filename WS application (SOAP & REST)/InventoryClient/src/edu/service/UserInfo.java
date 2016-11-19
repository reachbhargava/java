package edu.service;

import edu.service.UserInfo;

public class UserInfo {
	private static String userid = "";

	public static String getUserid() {
		return userid;
	}

	public static void setUserid(String userid) {
		UserInfo.userid = userid;
	}
	
}
