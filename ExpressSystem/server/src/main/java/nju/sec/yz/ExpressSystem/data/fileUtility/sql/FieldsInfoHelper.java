package nju.sec.yz.ExpressSystem.data.fileUtility.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FieldsInfoHelper<T> {
	
	public static final int TYPE_OF_FIELD=0;
	
	public static final int VALUE_OF_FIELD=1;
	
	private T entity;
	
	public FieldsInfoHelper(T entity) {
		this.entity=entity;
	}
	
	public void setEntity(T entity){
		this.entity=entity;
	}

	/**
	 * 根据属性名获取属性值
	 */
	private Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取属性类型(f_type)，属性名(f_name)，属性值(f_value)的map组成的list
	 */
	public Map<String,List< Object>> getFiledsInfo() {
		Field[] fields = entity.getClass().getDeclaredFields();
		Map<String,List<Object>> map = new HashMap<>();
		List<Object> infoMap;

		for (int i = 0; i < fields.length; i++) {
			infoMap = new ArrayList<Object>();
			infoMap.add(fields[i].getType().getSimpleName());
			
			Object value=getFieldValueByName(fields[i].getName(), entity);
			infoMap.add(value);
			
			map.put(fields[i].getName(),infoMap);
		}
		return map;
	}
	
	public List<String> getFieldsName(){
		Field[] fields = entity.getClass().getDeclaredFields();
		List<String> list=new ArrayList<>();
		for(int i = 0; i < fields.length ; i++){
			list.add(fields[i].getName());
		}
		return list;
	}
}
