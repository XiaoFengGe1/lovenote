package com.app.utils;

import org.springframework.stereotype.Component;

@Component
public class StringTools {

	/**
	 * String[] To Integer[]
	 * @param strArray
	 * @return
	 */
	public Integer[] stringArrayToIntArray(String[] strArray){
		Integer array[] = new Integer[strArray.length];  
		for(int i=0;i<strArray.length;i++){  
		    array[i]=new Integer(strArray[i]);
		}
		return array;
	}
}
