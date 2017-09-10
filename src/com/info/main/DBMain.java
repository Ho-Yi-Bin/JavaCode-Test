package com.info.main;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.info.constant.ConfigConstant;
import com.info.constant.LogConstant;
import com.info.manager.ConfigLoadManager;
import com.info.util.DbUtil;
import com.info.util.TableUtil;
import com.syscom.safe.util.xml.ElementNotFoundException;
import com.syscom.safe.util.xml.XmlAggregate;

public class DBMain {
	private static Logger mLogger = Logger.getLogger(LogConstant.SERVER_LOGGER);

	public static void main(String[] args) {
		initConfig();
		initDB();
		initTable();
		while(true) {
			
		}
	}
	
	/**
	 * 初始化讀取config
	 */
	private static void initConfig() {
		// 讀取 config 設定
		ConfigLoadManager.getInstance().init();
	}
	
	/**
	 * 初始化 DB 
	 */
	private static void initDB() {
		XmlAggregate agrDbService = ConfigLoadManager.getInstance().getDbServiceConfig();
		if (agrDbService == null) {
			mLogger.error(ConfigConstant.DB_CONFIG_NAME + "中找不到<db-service>, 請檢查...");
			System.exit(1);
		} else {
			try {
				DbUtil.getInstance().initConnect(agrDbService);
			} catch (ElementNotFoundException e) {
				mLogger.error("初始化連線 DB 工具失敗, 可能設定檔有問題", e);
				System.exit(1);
			} catch (ClassNotFoundException e) {
				mLogger.error("初始化連線 DB 工具失敗, 可能設定檔有問題", e);
				System.exit(1);
			} catch (SQLException e) {
				mLogger.error("初始化連線 DB 工具失敗, 可能設定檔有問題", e);
				System.exit(1);
			}
		}
	}
	
	private static void initTable() {
		XmlAggregate argTableService = ConfigLoadManager.getInstance().getTableInfoConfig();
		if (argTableService == null) {
			mLogger.error(ConfigConstant.TABLE_CONFIG_NAME + "中找不到<table>，請檢查...");
			System.exit(1);
		} else {
			try {
				TableUtil.getInstance().initTableRead(argTableService);
			} catch (ElementNotFoundException e) {
				mLogger.error("初始化處理table失敗, 可能設定檔有問題", e);
				System.exit(1);
			} catch (ClassNotFoundException e) {
				mLogger.error("初始化處理table失敗, 可能設定檔有問題", e);
				System.exit(1);
			} catch (SQLException e) {
				mLogger.error("初始化處理table失敗, 可能設定檔有問題", e);
				System.exit(1);
			}
		}
	}
}
