package nju.sec.yz.ExpressSystem.data.fileUtility.sql.uodate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.ConnectionHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.FieldsInfoHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.PrepareStatementHelper;

public class UpdateHelper {

	public <T> void update(T entity,String tableName,String primaryKey) throws SQLException{
		Connection conn=ConnectionHelper.getConn();
		UpdateSQLBuilder builder=new UpdateSQLBuilder();
		FieldsInfoHelper<T> fieldsInfoTool=new FieldsInfoHelper<T>(entity);
		List<String> fieldsNameList=fieldsInfoTool.getFieldsName();
		Map<String,List<Object>> infoMap=fieldsInfoTool.getFiledsInfo();
		if(!fieldsNameList.contains(primaryKey)){
			throw new SQLException("wrong primaryKey");
		}
		//去掉主键
		fieldsNameList.remove(primaryKey);
		String sql=builder.getSQL(tableName, primaryKey,fieldsNameList.iterator());
		PreparedStatement pst=conn.prepareStatement(sql);
		
		PrepareStatementHelper pstHelper=new PrepareStatementHelper();
		int i=1;
		for(String fieldName:fieldsNameList){
			List<Object> infoList=infoMap.get(fieldName);
			String type=(String) infoList.get(FieldsInfoHelper.TYPE_OF_FIELD);
			Object value=infoList.get(FieldsInfoHelper.VALUE_OF_FIELD);
			pstHelper.prepareStatement(pst, type, i, value);
			i++;
		}
	}
	
}
