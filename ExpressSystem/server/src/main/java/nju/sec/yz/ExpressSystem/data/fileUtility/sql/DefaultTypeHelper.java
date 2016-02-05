package nju.sec.yz.ExpressSystem.data.fileUtility.sql;


import java.util.HashMap;

import java.util.Map;
/**
 * 验证是否为自定义类型
 * String float double int
 * @author 周聪
 */
public class DefaultTypeHelper {

	private static Map<String,Class<?>> defaultTypeMap;
	
	static{
		defaultTypeMap=new HashMap<>();
		defaultTypeMap.put("int", int.class);
		defaultTypeMap.put("String", String.class);
		defaultTypeMap.put("float", float.class);
		defaultTypeMap.put("double", double.class);
	}
	
	public boolean isDefultType(String type){
		return defaultTypeMap.containsKey(type);
	}
	
	public Class<?> getDefaultClass(String type){
		if(!isDefultType(type))
			return null;
		return defaultTypeMap.get(type);
	}
	
}
