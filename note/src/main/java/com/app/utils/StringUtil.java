package com.app.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class StringUtil {
	static DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

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

	public static String toString(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	public static String getRandom() {
		return "" + VAR.getSixRandom();
	}

	public static String getTimeStr() {
		Calendar c = Calendar.getInstance();
		return format.format(c.getTime());
	}

	public static void main(String[] args) {
		System.out.println(getTimeStr());
	}
}
