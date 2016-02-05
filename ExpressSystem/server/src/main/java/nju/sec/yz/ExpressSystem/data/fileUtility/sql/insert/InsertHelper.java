package nju.sec.yz.ExpressSystem.data.fileUtility.sql.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.ConnectionHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.FieldsInfoGetter;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.PrepareStatementHelper;

public class InsertHelper {
	

	public <T> void insert(T entity,String tableName) throws SQLException{
		FieldsInfoGetter<T> fieldsInfoTool=new FieldsInfoGetter<>(entity);
		Map<String,List<Object>> fieldInfoMap = fieldsInfoTool.getFiledsInfo();
		List<String> fieldsNameList=fieldsInfoTool.getFieldsName();
		InsertSQLBuilder builder=new InsertSQLBuilder();
		String sql=builder.getSQL(tableName,fieldsNameList.iterator());//TODO entity.getClass.getSimpleName;
		
		Connection conn=ConnectionHelper.getConn();
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			
			prepareStatement(fieldsNameList.iterator(),fieldInfoMap, pst);
			
			pst.executeUpdate();
			pst.close();
		} finally{
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
			String type=(String)infoList.get(FieldsInfoGetter.TYPE_OF_FIELD);
			Object value=infoList.get(FieldsInfoGetter.VALUE_OF_FIELD);
			helper.prepareStatement(pst, type, i, value);
			i++;
		}
	}
	
}
