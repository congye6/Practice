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

public class InsertHelper {
	/**
	 * 获得内置类型的PreparedStatement的set方法
	 * String,int,float,double
	 */
	private static Map<String,Method> setterMap;
	
	static{
		setterMap=new HashMap<>();
		try {
			setterMap.put("String", PreparedStatement.class.getMethod("setString", int.class,String.class));
			setterMap.put("int", PreparedStatement.class.getMethod("setInt", int.class,int.class));
			setterMap.put("float", PreparedStatement.class.getMethod("setFloat", int.class,float.class));
			setterMap.put("double", PreparedStatement.class.getMethod("setDouble", int.class,double.class));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public <T> void insert(T entity,String tableName) throws SQLException{
		Iterator<Map<String, Object>> fieldInfoIterator = FieldsInfoTool.getFiledsInfo(entity);
		InsertSQLBuilder builder=new InsertSQLBuilder();
		String sql=builder.getSQL(tableName,fieldInfoIterator);//TODO entity.getClass.getSimpleName;
		
		Connection conn=ConnectionHelper.getConn();;
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			
			prepareStatement(fieldInfoIterator, pst);
			
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			throw e;
		}finally{
			conn.close();
		}
	}
	
	//设置值
	private void prepareStatement(Iterator<Map<String, Object>> iteraor, PreparedStatement pst) throws SQLException {
		int i=0;
		while(iteraor.hasNext()){
			Map<String, Object> fieldInfo=iteraor.next();
			String type=(String)fieldInfo.get(FieldsInfoTool.TYPE_OF_FIELD);
			if(setterMap.containsKey(type)){
				Method method=setterMap.get(type);
				try {
					method.invoke(pst, i+1 , fieldInfo.get(FieldsInfoTool.VALUE_OF_FIELD));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}else{
				pst.setObject(i+1, InsertObjectHelper.serialize(fieldInfo.get(FieldsInfoTool.VALUE_OF_FIELD)));
			}
			i++;
		}
	}
	
}
