package nju.sec.yz.ExpressSystem.data.fileUtility.sql.find;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.ConnectionHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.PrepareStatementHelper;

public class FindHelper {
	
	private static final String SQL="select * from ";

	public <T> List<T> find(Class<?> entityClass,String key,String tableName,Object value,String type) throws SQLException{
		
		String sql=SQL+tableName+" where "+key+" =?";
		
		List<T> resultList=new ArrayList<>();
		Connection conn=ConnectionHelper.getConn();
		try{
			PreparedStatement pst=conn.prepareStatement(sql);
			PrepareStatementHelper helper=new PrepareStatementHelper();
			helper.prepareStatement(pst, type, 1, value);
			resultList=findFromSql(pst, entityClass);
		}  finally{
			conn.close();
		}
		
		
		return resultList;
	}
	
	public <T> List<T> find(Class<?> entityClass,String tableName) throws SQLException{
		List<T> resultList=new ArrayList<>();
		String sql=SQL+tableName;
		Connection conn=ConnectionHelper.getConn();
		try{
			PreparedStatement pst=conn.prepareStatement(sql);
			resultList=findFromSql(pst, entityClass);
		}finally{
			conn.close();
		}
		return resultList;
	}
	
	private <T> List<T> findFromSql(PreparedStatement pst,Class<?> entityClass) throws  SQLException{
		
		FieldsInfoSetter<T> setter=new FieldsInfoSetter<>();
		List<T> resultList=new ArrayList<>();
		ResultSet resultSet=pst.executeQuery();
		T entity;
		while(resultSet.next()){
			try {
				entity=(T) entityClass.newInstance();
				setter.setValue(entity, resultSet);
				resultList.add(entity);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}
	
	
}
