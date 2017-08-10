package com.app.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class StringUtil {
	static DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	public static int parseInt(Object str) {
		String numStr = toString(str);
		if (numStr.matches(VAR.matchNumber)) {
			return Integer.parseInt(numStr);
		} else {
			System.err.println("StringUtil.parseInt.int转换错误。str=" + str);
			return 0;
		}
	}

	/**
	 * @return 获取合法字符
	 */
	public static String getLegalStr(String str) {
		if (isBlank(str)) {
			return "";
		}
		if (str.matches(VAR.matchLegal)) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		String[] strs = str.split("");
		for (String s : strs) {
			if (s.matches(VAR.matchLegal)) {
				sb.append(s);
			}
		}
		return sb.toString();
	}

	public static String[] getLegalStrs(String[] strs) {
		if (strs != null && strs.length > 0) {
			ArrayList<String> list = new ArrayList<String>();
			for (String s : strs) {
				String s2 = getLegalStr(s);
				if (!StringUtil.isBlank(s2)) {
					list.add(s2);
				}
			}
			if (list.size() > 0) {
				String[] strsNew = new String[list.size()];
				int i = 0;
				for (String s : list) {
					strsNew[i] = s;
					i++;
				}
				return strsNew;
			}
		}
		return null;
	}

	public static boolean isBlank(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			if ("".equals(obj.toString().trim()) || obj.toString().isEmpty()) {
				return true;
			}
		} else if (obj instanceof Map) {
			Map m = (Map) obj;
			if (m == null || m.isEmpty()) {
				return true;
			}
		} else if (obj instanceof List) {
			List l = (List) obj;
			if (l == null || l.isEmpty()) {
				return true;
			}
		} else {
			if ("".equals(obj.toString().trim()) || obj.toString().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 不为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotBlank(Object obj) {
		return !isBlank(obj);
	}

	public static String toString(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	public static String toString(Object str) {
		if (str == null) {
			return "";
		}
		return toString(str.toString());
	}

	public static String getRandom() {
		return "" + VAR.getSixRandom();
	}

	public static String getTimeStr() {
		Calendar c = Calendar.getInstance();
		return format.format(c.getTime());
	}

	public static void main(String[] args) {
		Integer i = 2;
		System.out.println(i.toString());
	}
}
