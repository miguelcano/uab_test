package com.uab.project.utils;

import java.util.Collection;

public class CheckObject {
	public static boolean isNull(Object object) {
		return object == null ? true : false; 
	}
	
	public static boolean isNullOrEmpty(String object) {
		return object == null ? true : object.isEmpty() ? true : false; 
	}
	
	public static <T> boolean isNullOrEmptyList(Collection<T> object) {
		return object == null ? true : object.isEmpty() ? true : false; 
	}
}
