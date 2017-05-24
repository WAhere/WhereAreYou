package com.example.whereareyou;

//数据结构
public class AppContext {
	private static String number;		//主控手机号码类型为字符串
	//以下都是选定的布尔类型按钮选择
	private static boolean phone;		//监控手机
	private static boolean sms;			//短信
	private static boolean callback;	//回拨
	private static boolean ring;		//响铃
	private static boolean vibra;		//振动
	private static boolean listen;		//监听
	
	//此处设置布尔量监听是否规范（开始监控选项是否被选中）
	public static boolean isListen() {
		return listen;
	}

	//此处设置监听类的方法（AppContext提供用于设置和检索应用程序上下文相关数据的成员）
	public static void setListen(boolean listen) {
		AppContext.listen = listen;
	}

	//此处设置布尔量监控手机是否规范（监控手机是否被选中）
	public static boolean isPhone() {
		return phone;
	}

	//此处设置监控手机类的方法（AppContext提供用于设置和检索应用程序上下文相关数据的成员）
	public static void setPhone(boolean phone) {
		AppContext.phone = phone;
	}

	//监控短信，描述类上
	public static boolean isSms() {
		return sms;
	}

	public static void setSms(boolean sms) {
		AppContext.sms = sms;
	}

	//回拨电话，描述类上
	public static boolean isCallback() {
		return callback;
	}

	public static void setCallback(boolean callback) {
		AppContext.callback = callback;
	}

	//响铃，描述类上
	public static boolean isRing() {
		return ring;
	}

	public static void setRing(boolean ring) {
		AppContext.ring = ring;
	}

	//振动，描述类上
	public static boolean isVibra() {
		return vibra;
	}

	public static void setVibra(boolean vibra) {
		AppContext.vibra = vibra;
	}

	//公共静态字符串（主控手机）号码
	public static String getNumber() {
		return number;
	}

	public static void setNumber(String number) {
		AppContext.number = number;
	}
	
}
