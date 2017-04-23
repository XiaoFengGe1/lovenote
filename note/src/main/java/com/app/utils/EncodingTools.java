package com.app.utils;

import java.io.UnsupportedEncodingException;

public class EncodingTools {
    public static String encodeStr(String str) {
        try {
        	if(str!=null && !str.equals(""))
        		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
		return null;
    }  
}