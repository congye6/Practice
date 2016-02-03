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
		String setter=SetterNameHelper.getSetterName(type);
		Method method=null;
		try {
			method=PreparedStatement.class.getMethod(setter,int.class,Class.forName(type));
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return method;
	}
	
	public void prepareStatement(PreparedStatement pst,String type,int i,Object value) throws SQLException{
		DefaultTypeHelper tester=new DefaultTypeHelper();
		if(tester.isDefultType(type)){
			Method method=this.getSetter(type);
			try {
				method.invoke(pst, i , value);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}else{
			pst.setObject(i, SerializetHelper.serialize(value));
		}
	}
}
