package nju.sec.yz.ExpressSystem.data.fileUtility.sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.insert.InsertObjectHelper;

public class PrepareStatementHelper {
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
	
	public void prepareStatement(PreparedStatement pst,String type,int i,Object value) throws SQLException{
		if(setterMap.containsKey(type)){
			Method method=setterMap.get(type);
			try {
				method.invoke(pst, i+1 , value);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}else{
			pst.setObject(i+1, InsertObjectHelper.serialize(value));
		}
	}
}
