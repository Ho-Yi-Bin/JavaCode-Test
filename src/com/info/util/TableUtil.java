package com.info.util;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.info.constant.LogConstant;
import com.syscom.safe.util.xml.ElementNotFoundException;
import com.syscom.safe.util.xml.XmlAggregate;
import com.syscom.safe.util.xml.XmlElement;

public class TableUtil {
	private Logger mLogger = Logger.getLogger(LogConstant.SERVER_LOGGER);

	private static TableUtil instance = new TableUtil();

	private List<XmlAggregate> agrAllTables, agrAllColumns;

	private TableUtil() {

	}

	public static TableUtil getInstance() {
		if (instance == null) {
			instance = new TableUtil();
		}
		return instance;
	}

	/**
	 *  Table 處理設定初始化
	 * @param argTableService
	 * @throws ElementNotFoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public void initTableRead(XmlAggregate argTableService) throws ElementNotFoundException, ClassNotFoundException, SQLException {
		agrAllTables = argTableService.getAggregates("table");
		for (XmlAggregate agrTable : agrAllTables) {
			System.out.println(agrTable.getAttribValue("id") + "-" + agrTable.getAttribValue("tablename"));
			agrAllColumns = agrTable.getAggregates("columns");
			for (XmlAggregate agrColumns : agrAllColumns) {
				List<XmlElement> agrAllColumnCodes = agrColumns.getElements("column");
				for (XmlElement agrColumnCode : agrAllColumnCodes) {
					String sCid 		= agrColumnCode.getAttribValue("cid");
					String sType		= agrColumnCode.getAttribValue("type");
					String sColumnname	= agrColumnCode.getAttribValue("columnname");
					System.out.println(sCid + "-" + sType + "-" + sColumnname);
				}
			}
		}
	}
}
