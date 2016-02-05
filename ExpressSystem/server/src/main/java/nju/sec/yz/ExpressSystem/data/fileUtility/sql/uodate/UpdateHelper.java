package nju.sec.yz.ExpressSystem.data.fileUtility.sql.uodate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.ConnectionHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.FieldsInfoGetter;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.PrepareStatementHelper;

public class UpdateHelper {

	public <T> void update(T entity,String tableName,String primaryKey) throws SQLException{
		Connection conn=ConnectionHelper.getConn();
		UpdateSQLBuilder builder=new UpdateSQLBuilder();
		FieldsInfoGetter<T> fieldsInfoTool=new FieldsInfoGetter<T>(entity);
		List<String> fieldsNameList=fieldsInfoTool.getFieldsName();
		Map<String,List<Object>> infoMap=fieldsInfoTool.getFiledsInfo();
		if(!fieldsNameList.contains(primaryKey)){
			throw new SQLException("wrong primaryKey");
		}
		//去掉主键
		fieldsNameList.remove(primaryKey);
		String sql=builder.getSQL(tableName, primaryKey,fieldsNameList.iterator());
		try{
			PreparedStatement pst=conn.prepareStatement(sql);
			prepareStatement(primaryKey, fieldsNameList, infoMap, pst);
			pst.executeUpdate();
			pst.close();
		}finally{
			conn.close();
		}
		
		
	}

	/**
	 * 设置prepareStatement所有变量的值
	 * @throws SQLException
	 */
	private void prepareStatement(String primaryKey, List<String> fieldsNameList, Map<String, List<Object>> infoMap,
			PreparedStatement pst) throws SQLException {
		PrepareStatementHelper pstHelper=new PrepareStatementHelper();
		int i=1;
		for(String fieldName:fieldsNameList){
			setStatementValue(infoMap, pst, pstHelper, i, fieldName);
			i++;
		}
		setStatementValue(infoMap, pst, pstHelper, i, primaryKey);
	}

	/**
	 * 设置prepareStatement一个变量的值
	 * @throws SQLException
	 */
	private void setStatementValue(Map<String, List<Object>> infoMap, PreparedStatement pst,
			PrepareStatementHelper pstHelper, int i, String fieldName) throws SQLException {
		List<Object> infoList=infoMap.get(fieldName);
		String type=(String) infoList.get(FieldsInfoGetter.TYPE_OF_FIELD);
		Object value=infoList.get(FieldsInfoGetter.VALUE_OF_FIELD);
		pstHelper.prepareStatement(pst, type, i, value);
	}
	
}
