package nju.sec.yz.ExpressSystem.data.fileUtility.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldsInfoTool {
	
	public static final String TYPE_OF_FIELD="f_type";
	
	public static final String NAME_OF_FIELD="f_name";
	
	public static final String VALUE_OF_FIELD="f_value";
	
	/**
	 * 根据属性名获取属性值
	 */
	static private Object getFieldValueByName(String fieldName, Object o) {
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
	static public <T> List<Map<String, Object>> getFiledsInfo(T o) {
		Field[] fields = o.getClass().getDeclaredFields();
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> infoMap;

		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap<String, Object>();
			infoMap.put(TYPE_OF_FIELD, fields[i].getType().getSimpleName());
			infoMap.put(NAME_OF_FIELD, fields[i].getName());
			
			Object value=getFieldValueByName(fields[i].getName(), o);
			infoMap.put(VALUE_OF_FIELD, value);
			
			list.add(infoMap);
		}
		return list;
	}
}
