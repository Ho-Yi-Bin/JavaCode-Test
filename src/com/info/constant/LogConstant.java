package com.info.constant;

public interface LogConstant {
	/**
	 * log4j appenders
	 */
	public static final String SERVER_LOGGER = "com.info.main";
	
	/**
	 * log4j 檔名
	 */
	public static final String LOG4J_NAME = "log4j.properties";
	
	/**
	 * log4j 位置
	 */
	public static final String LOG4J_PATH = "../config/" + LOG4J_NAME;
	
	/**
	 * log4j 位置(測試版)
	 */
	public static final String LOG4J_TEST_PATH = "./config/" + LOG4J_NAME;
	
	/**
	 * log4j 啟動成功
	 */
	public static final String LOG_SUCCESS = "Log 機制啟動成功";
	
	/**
	 * log4j 啟動失敗
	 */
	public static final String LOG_FAIL = "log機制啟動失敗";
}
