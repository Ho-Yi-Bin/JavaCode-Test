package com.info.manager;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.info.constant.ConfigConstant;
import com.info.constant.LogConstant;
import com.syscom.safe.util.xml.XmlAggregate;
import com.syscom.safe.util.xml.XmlException;
import com.syscom.safe.util.xml.XmlParser;

public class ConfigLoadManager {
	private Logger mLogger = Logger.getLogger(LogConstant.SERVER_LOGGER);
	
	private static ConfigLoadManager instance = new ConfigLoadManager();
	
	private XmlAggregate agrDbService , agrTableInfo;
	
	private ConfigLoadManager () {
		
	}
	
	public static ConfigLoadManager getInstance() {
		if (instance == null) {
			instance = new ConfigLoadManager();
		}
		return instance;
	}
	
	/**
	 * 讀入 所有 config
	 */
	public void init () {
		loadLog4J();
		loadDBConfig();
		loadTableConfig();
	} 
	
	
	/**
	 * 取得log4j 資訊
	 */
	private void loadLog4J() {
		String sLog4jPath = LogConstant.LOG4J_PATH;
		
		
		File onlineLog4jSetting = new File(sLog4jPath);
		if (!onlineLog4jSetting.isFile()) {
			sLog4jPath = LogConstant.LOG4J_TEST_PATH;
		}
	
		PropertyConfigurator.configure(sLog4jPath);
	
		mLogger.info(LogConstant.LOG_SUCCESS);
		//mLogger.info(StrConstant.SEPRATE_LINE);
		
	}

	/**
	 * 取得DB 連線訊息
	 */
	private void loadDBConfig() {
		String sDBConfigPath = ConfigConstant.DB_CONFIF_PATH;
		
		// 檢查 config 路徑是否正確，若不正確則換一個路徑
		File fConfigFile = new File(sDBConfigPath);
		if (!fConfigFile.isFile()) {
			sDBConfigPath = ConfigConstant.TEST_DB_CONFIG_PATH;
		}
		
		// 開始 parser db-info.xml
		try {
			XmlAggregate agrDBConfig = XmlParser.parseXmlFile(sDBConfigPath);
			
			parseDbService(agrDBConfig);
			
		} catch (FileNotFoundException e) {
			mLogger.error("請確認 config 目錄底下存在 db-info.xml" + e);
			System.exit(1);
		} catch (XmlException e) {
			mLogger.error("請確認 config 目錄底下 db-info.xml 為合法的 xml 格式" + e);
			System.exit(1);
		}
	}
	
	/**
	 * 取得Table 連線訊息
	 */
	private void loadTableConfig() {
		String sTableConfigPath = ConfigConstant.TABLE_CONFIG_PATH;
		
		// 檢查 config 路徑是否正確，若不正確則換一個路徑
		File fConfigFile = new File(sTableConfigPath);
		if (!fConfigFile.isFile()) {
			sTableConfigPath = ConfigConstant.TEST_TABLE_CONFIG_PATH;
		}
		
		// 開始 parser table-info.xml
		try {
			XmlAggregate agrTableConfig = XmlParser.parseXmlFile(sTableConfigPath);
			
			parseTableInfo(agrTableConfig);
			
		} catch (FileNotFoundException e) {
			mLogger.error("請確認 config 目錄底下存在 table-info.xml" + e);
			System.exit(1);
		} catch (XmlException e) {
			mLogger.error("請確認 config 目錄底下 table-info.xml 為合法的 xml 格式" + e);
			System.exit(1);
		}
	}

	/**
	 * 處理DB連線部分
	 * @param agrDBConfig
	 */
	private void parseDbService (XmlAggregate agrDBConfig) {
		agrDbService = agrDBConfig.getAggregate("db-service");
		if (agrDbService == null) {
			mLogger.error("請確認db-info.xml中是否存在<db-service>");
		}
	}
	
	/**
	 * 處理Table部分
	 * @param agrTableConfig
	 */
	private void parseTableInfo (XmlAggregate agrTableConfig) {
		agrTableInfo = agrTableConfig.getAggregate("tables");
		if (agrTableInfo == null) {
			mLogger.error("請確認table-info.xml中是否存在 <tables>");
		}
	}
	
	/**
	 * 取得DB設定狀態
	 * @return
	 */
	public XmlAggregate getDbServiceConfig() {
		return agrDbService;
	}
	
	/**
	 *  取得Table設定狀態
	 * @return
	 */
	public XmlAggregate getTableInfoConfig() {
		return agrTableInfo;
	}
}
