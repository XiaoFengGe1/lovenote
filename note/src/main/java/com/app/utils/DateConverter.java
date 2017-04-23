package com.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<Date, String> {
	@Override
	public String convert(Date source) {System.out.println(source);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			return dateFormat.format(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}