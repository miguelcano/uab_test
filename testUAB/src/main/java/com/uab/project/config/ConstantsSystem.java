package com.uab.project.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConstantsSystem {
	
	//ip de servidor de producción
	public static final String PRODUCTION_IP = "10.21.0.1";

	public static final long EXPIRATION_TIME = 86400000; // 1 day
	
	public static final String PRODUCTION_URL = "http://wsvc.novotempo.com/";
	public static final String HOMOLOGATION_URL = "http://localhost:8887/";
	
	//url de resource de autenticación
	public static final String AUTHENTICATION = "authentication";
	public static final String SWAGGER = "swagger-ui.html";
	
	public static final String TOKEN = "4051e42ab88d33168a29abc8625def41";
	
	public static String getURL() {
		return isProduction() ? PRODUCTION_URL : HOMOLOGATION_URL;
	}
	
	public static boolean isProduction() {
		return getIP() != null && getIP().equals(PRODUCTION_IP);
	}
	
	private static String getIP() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			return address.getHostAddress();
		} catch (UnknownHostException e) {}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getIP());
	}
}
