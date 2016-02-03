package nju.sec.yz.ExpressSystem.data.fileUtility.sql;

/**
 * 获得相应属性的getter
 * getter命名规则：get+属性名
 * @author 周聪
 */
public class GetterNameHelper {

	public static String getGetterName(String fieldName){
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + fieldName.substring(1);
		return getter;
	}
	
}
