package com.app.utils;

import java.util.Random;

/**
 * 全局变量类
 * 
 * @date 2016年1月18日
 * @verison 1.00
 * @author lixiaofeng
 * @see
 */
public class VAR {
	public static int sixNum = 0;
	// 几乎所有字符，除去.@
	public static String regEx = "[\\s~`!！#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
	public static String matchLegal = "^[a-zA-Z0-9@_.#\u4E00-\u9FA5]+$";
	public static String matchIdcard = "^([0-9]{17}([0-9]|X))|([0-9]{15})$";
	public static String matchAccount = "^[0-9]{5,15}$";
	public static String matchNumber = "^[0-9]{1,50}$";
	public static String matchEmail = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	public static String matchPassword = "^[a-zA-Z0-9]{6,50}$";
	public static String matchTel = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(170))\\d{8}$";
	public static String matchBarcode = "^[0-9]{10,20}$";// 匹配条形码，时间问题简单代替。
	public static String matchBankCard = "^[0-9]{10,20}$";// 匹配银行卡，时间问题简单代替。
	public static String matchPrice = "^[0-9]{1,15}+(\\.[0-9]{1,2})?$";
	public static String userName = "";
	public static String userEmail = "";
	public static long userAccount = 0;
	public static String baseStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	// 个人中心在保存密码时候传该值代表不修改密码
	public static String passwordCheck = "562150580a896eca18906e51dda79acc";
	// 基本密码的加盐
	public static String passwordSalt = "e2ATh95jd";

	public static int getSixRandom() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < 6; i++)
			result = array[i] + result * 10;
		if (result < 100000) {
			return result * 10;
		}
		return result;
	}

	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(baseStr.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String str = "11@fs-.d";
		System.out.println(getRandomString(20));
		for (int i = 0; i < 0; i++) {

			System.out.println(getSixRandom());
		}
	}
}