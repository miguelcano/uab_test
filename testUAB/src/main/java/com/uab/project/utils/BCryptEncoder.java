package com.uab.project.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String secret = bCryptPasswordEncoder.encode("secret"); //secret
		System.out.println(secret);
		
		String password = bCryptPasswordEncoder.encode("password"); //password
		System.out.println(password);
		
	}
	
}

