package com.example.whereareyou;

public class AppContext {
	private static String number;
	private static boolean phone;
	private static boolean sms;
	private static boolean callback;
	private static boolean ring;
	private static boolean vibra;
	private static boolean listen;
	
	public static boolean isListen() {
		return listen;
	}

	public static void setListen(boolean listen) {
		AppContext.listen = listen;
	}

	public static boolean isPhone() {
		return phone;
	}

	public static void setPhone(boolean phone) {
		AppContext.phone = phone;
	}

	public static boolean isSms() {
		return sms;
	}

	public static void setSms(boolean sms) {
		AppContext.sms = sms;
	}

	public static boolean isCallback() {
		return callback;
	}

	public static void setCallback(boolean callback) {
		AppContext.callback = callback;
	}

	public static boolean isRing() {
		return ring;
	}

	public static void setRing(boolean ring) {
		AppContext.ring = ring;
	}

	public static boolean isVibra() {
		return vibra;
	}

	public static void setVibra(boolean vibra) {
		AppContext.vibra = vibra;
	}


	public static String getNumber() {
		return number;
	}

	public static void setNumber(String number) {
		AppContext.number = number;
	}
	
}
