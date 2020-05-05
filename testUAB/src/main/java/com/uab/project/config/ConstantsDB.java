package com.uab.project.config;

public class ConstantsDB {

	//usuario y password base de datos test para homologación
	private static final String TEST_USER_HOMOLOGATION = "miguel.ayala";
	private static final String TEST_PASSWORD_HOMOLOGATION = "password";

	//usuario y password base de datos test para producción
	private static final String TEST_USER_PRODUCTION = "userdd";
	private static final String TEST_PASSWORD_PRODUCTION = "pasdd";

	public static String getTestUser() { 
		return ConstantsSystem.isProduction() ? TEST_USER_PRODUCTION : TEST_USER_HOMOLOGATION;
	}

	public static String getTestPassword() {
		return ConstantsSystem.isProduction() ? TEST_PASSWORD_PRODUCTION : TEST_PASSWORD_HOMOLOGATION;
	}

	/**
	 * ip de base de datos
	 * @return
	 */
	public static String getIpDb() {
		return ConstantsSystem.isProduction() ? "100.210.0.21" : "10.21.0.25";
	}

}