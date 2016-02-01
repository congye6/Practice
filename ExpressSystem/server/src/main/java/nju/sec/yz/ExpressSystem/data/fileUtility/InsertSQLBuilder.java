package nju.sec.yz.ExpressSystem.data.fileUtility;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.po.DriverPO;

public class InsertSQLBuilder {

	public String getSQL(String tableName,Iterator<Map<String,Object>> iterator){
		String sql = "Insert into ";
		String column = ""; // 列
		String c_values = ""; // 列值
		
		
		sql += tableName + " ";//entity.getClass().getName();
		while(iterator.hasNext()) {
			Map<String,Object> fieldInfo=iterator.next();
			if (fieldInfo.get("f_value") != null) {
				column += fieldInfo.get("f_name") + ",";
				c_values +=  "?,";
			}
		}
		sql += "(" + column.substring(0, column.length() - 1) + ") values ("
				+ c_values.substring(0, c_values.length() - 1) + ");";
		
		return sql;

	}
	
	/*public static void main(String[] args) {
		DriverPO po=new DriverPO("025001B003", "hh", "19560312", "4378324623482348934", "1234455666", Sex.FEMALE,"025001", "20170213");
		List<Map<String, Object>> fieldInfoList = FieldsInfoTool.getFiledsInfo(po);
		System.out.println(new InsertSQLBuilder().getSQL("driver", fieldInfoList.iterator()));
	}*/
}
