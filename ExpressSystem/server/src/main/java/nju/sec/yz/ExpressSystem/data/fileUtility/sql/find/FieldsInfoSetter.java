package nju.sec.yz.ExpressSystem.data.fileUtility.sql.find;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.DefaultTypeHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.FieldsInfoGetter;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.GetterNameHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.SerializetHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.SetterNameHelper;

public class FieldsInfoSetter<T> {
	
	public void setValue(T entity,ResultSet resultSet) throws SQLException{
		FieldsInfoGetter<T> getter=new FieldsInfoGetter<>(entity);
		List<String> fieldsNameList=getter.getFieldsName();
		Map<String,List<Object>> fieldsInfo=getter.getFiledsInfo();
		for(String fieldName:fieldsNameList){
			List<Object> infoList=fieldsInfo.get(fieldName);
			String type=(String)infoList.get(FieldsInfoGetter.TYPE_FULLNAME_OF_FIELD);
			String typeSimpleName=(String)infoList.get(FieldsInfoGetter.TYPE_OF_FIELD);
			Object value=getValue(resultSet, typeSimpleName, fieldName);
			setFieldValue(entity, fieldName, type,value);
		}
	}

	private Object getValue(ResultSet resultSet,String type,String fieldName) throws SQLException{
		Object result=null;
		Method method=getGetMethod(type);
		if(method!=null){
			try {
				result=method.invoke(resultSet, fieldName);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}else{
			result=SerializetHelper.deSerialize(resultSet.getBlob(fieldName));
		}
		return result;
	}
	
	private Method getGetMethod(String type){
		DefaultTypeHelper helper=new DefaultTypeHelper();
		if(!helper.isDefultType(type))
			return null;
		String getter=GetterNameHelper.getGetterName(type);
		Method method=null;
		try {
			method=ResultSet.class.getMethod(getter, String.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return method;
	}

	private void setFieldValue(T entity, String fieldName, String type,Object value) {
		Method method=getSetMethod(entity.getClass(), fieldName, type);
		try {
			method.invoke(entity, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	private Method getSetMethod(Class<?> entityClass, String fieldName, String type) {
		String setter=SetterNameHelper.getSetterName(fieldName);
		Method method=null;
		try {
			method=entityClass.getMethod(setter, Class.forName(type));
		} catch (NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return method;
	}
	
	
}
