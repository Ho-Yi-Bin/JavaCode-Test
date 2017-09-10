package com.info.constant;

public interface ConfigConstant {
	/**
	 * DB config 檔名
	 */
	public static final String DB_CONFIG_NAME = "db-info.xml";
	
	/**
	 * Table config 檔名
	 */
	public static final String TABLE_CONFIG_NAME = "table-info.xml";
	
	/**
	 * DB config 位置
	 */
	public static final String DB_CONFIF_PATH = "../../../config/" + DB_CONFIG_NAME;
	
	/**
	 * DB config 位置	(測試版)
	 */
	public static final String TEST_DB_CONFIG_PATH = "./config/" + DB_CONFIG_NAME;
	
	/**
	 * Table config 位置
	 */
	public static final String TABLE_CONFIG_PATH = "../../../config/" + TABLE_CONFIG_NAME;
	
	/**
	 * Table config 位置 (測試版)
	 */
	public static final String TEST_TABLE_CONFIG_PATH = "./config/" + TABLE_CONFIG_NAME;
	
}
