package nju.sec.yz.ExpressSystem.data.fileUtility.sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepareStatementHelper {
	/**
	 * 获得内置类型的PreparedStatement的set方法
	 */
	private Method getSetter(String type){
		
		DefaultTypeHelper helper=new DefaultTypeHelper();
		Class<?> typeClass=helper.getDefaultClass(type);
		if(typeClass==null)
			return null;
		
		String setter=SetterNameHelper.getSetterName(type);
		
		Method method=null;
		try {
			method=PreparedStatement.class.getMethod(setter,int.class,typeClass);
		} catch (NoSuchMethodException | SecurityException e ){
			e.printStackTrace();
		}
		return method;
	}
	
	public void prepareStatement(PreparedStatement pst,String type,int i,Object value) throws SQLException{
		
		Method method = this.getSetter(type);
		if (method != null) {
			try {
				method.invoke(pst, i, value);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			pst.setObject(i, SerializetHelper.serialize(value));
		}
	}
}
