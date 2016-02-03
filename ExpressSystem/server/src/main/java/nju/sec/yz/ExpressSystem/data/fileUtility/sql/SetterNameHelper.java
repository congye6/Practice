package nju.sec.yz.ExpressSystem.data.fileUtility.sql;
/**
 * 获得相应属性的setter
 * setter命名规则：set+属性名
 * @author 周聪
 */
public class SetterNameHelper {
	public static String getSetterName(String fieldName){
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String setter = "set" + firstLetter + fieldName.substring(1);
		return setter;
	}
}
