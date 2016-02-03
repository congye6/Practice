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
	}
	
	public boolean isDefultType(String type){
		return defaultTypeMap.containsKey(type);
	}
	
	public Class<?> getDefaultClass(String type){
		return defaultTypeMap.get(type);
	}
	
}
