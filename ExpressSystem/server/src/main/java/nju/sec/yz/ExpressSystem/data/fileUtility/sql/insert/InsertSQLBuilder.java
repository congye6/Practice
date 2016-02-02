package nju.sec.yz.ExpressSystem.data.fileUtility.sql.insert;


import java.util.Iterator;
import java.util.Map;

public class InsertSQLBuilder {

	public String getSQL(String tableName,Iterator<String> iterator){
		String sql = "Insert into ";
		String column = ""; // 列
		String c_values = ""; // 列值
		
		
		sql += tableName + " ";//entity.getClass().getName();
		while(iterator.hasNext()) {
			String fieldInfo = iterator.next();

			column += fieldInfo + ",";
			c_values += "?,";
			
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
