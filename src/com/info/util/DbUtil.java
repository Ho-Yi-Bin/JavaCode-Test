package com.info.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.info.constant.ConfigConstant;
import com.info.constant.LogConstant;
import com.syscom.safe.util.xml.ElementNotFoundException;
import com.syscom.safe.util.xml.XmlAggregate;

public class DbUtil {
	private static Logger mLogger = Logger.getLogger(LogConstant.SERVER_LOGGER);
	
	private static DbUtil instance = new DbUtil();
	
	private String sConnUrl , sConnDriver, sUserName, sPassword;
	
	private Connection conn;
	
	private DbUtil () {
		
	}
	
	public static DbUtil getInstance () {
		if (instance == null) {
			instance = new DbUtil();
		}
		return instance;
	}
	
	
	/**
	 * DB 連線設定初始化
	 * @param agrDbService
	 * @throws ElementNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void initConnect(XmlAggregate agrDbService) throws ElementNotFoundException, ClassNotFoundException, SQLException {
		// 連線位置
		try {
			sConnUrl = agrDbService.getElementStringValue("connection-url");
		} catch (ElementNotFoundException e) {
			throw new ElementNotFoundException("在"+ ConfigConstant.DB_CONFIG_NAME + "中的<db-service>裡找不到<connection-url>， 請檢查...");
		}
		// 連線 driver
		try {
			sConnDriver = agrDbService.getElementStringValue("driver-class");
		} catch (ElementNotFoundException e) {
			throw new ElementNotFoundException("在"+ ConfigConstant.DB_CONFIG_NAME + "中的<db-service>裡找不到<driver-class> ， 請檢查...");
		}
		// 登入帳號
		try {
			sUserName = agrDbService.getElementStringValue("user-name");
		} catch (ElementNotFoundException e) {
			throw new ElementNotFoundException("在"+ ConfigConstant.DB_CONFIG_NAME + "中的<db-service>裡找不到<user-name> ， 請檢查...");
		}
		// 登入 密碼
		try {
			sPassword = agrDbService.getElementStringValue("password");
		} catch (ElementNotFoundException e) {
			throw new ElementNotFoundException("在"+ ConfigConstant.DB_CONFIG_NAME + "中的<db-service>裡找不到<password> ， 請檢查...");
		}
		
		//  Oracle connection
		try {
			Class.forName(sConnDriver);
			mLogger.info("連接成功MySQLToJava");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("請檢查 driver-class: <"+ sConnDriver + "> 是否存在");
		}

		// 取得連線
		try {
			conn = DriverManager.getConnection(sConnUrl, sUserName, sPassword);
			conn.setAutoCommit(false);

			mLogger.info("連線 DB connUrl: <" + sConnUrl+ "> , userName: <" + sUserName + ">, password: <" + sPassword + "> 成功");

		} catch (SQLException e) {
			throw new SQLException("連線  "
									+ "DB connUrl: <"+ sConnUrl + ">, "
									+ "userName: <" + sUserName+ ">, "
									+ "password: <" + sPassword + "> 失敗, "
									+ "msg: <"+ e.getMessage() + ">");
		}
	}
	 
}
