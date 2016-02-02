package nju.sec.yz.ExpressSystem.data.fileUtility.sql.insert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.ConnectionHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.FieldsInfoTool;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.PrepareStatementHelper;

public class InsertHelper {
	

	public <T> void insert(T entity,String tableName) throws SQLException{
		FieldsInfoTool<T> fieldsInfoTool=new FieldsInfoTool<>(entity);
		Map<String,List<Object>> fieldInfoMap = fieldsInfoTool.getFiledsInfo();
		List<String> fieldsNameList=fieldsInfoTool.getFieldsName();
		InsertSQLBuilder builder=new InsertSQLBuilder();
		String sql=builder.getSQL(tableName,fieldsNameList.iterator());//TODO entity.getClass.getSimpleName;
		
		Connection conn=ConnectionHelper.getConn();;
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			
			prepareStatement(fieldsNameList.iterator(),fieldInfoMap, pst);
			
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			throw e;
		}finally{
			conn.close();
		}
	}
	
	//设置值
	private void prepareStatement(Iterator<String> iterator,Map<String,List<Object>> map, PreparedStatement pst) throws SQLException {
		int i=0;
		PrepareStatementHelper helper=new PrepareStatementHelper();
		while(iterator.hasNext()){
			String fieldName=iterator.next();
			List<Object> infoList=map.get(fieldName);
			String type=(String)infoList.get(FieldsInfoTool.TYPE_OF_FIELD);
			helper.prepareStatement(pst, type, i, infoList);
			i++;
		}
	}
	
}
