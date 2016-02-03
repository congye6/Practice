package nju.sec.yz.ExpressSystem.data.fileUtility.sql.uodate;

import java.util.Iterator;


public class UpdateSQLBuilder {

	public String getSQL(String tableName,String primaryKey,Iterator<String> fieldsNameIterator) {
	    
	    String sql = "update "+tableName+" set ";
	    
	    while(fieldsNameIterator.hasNext()){
	    	sql+=fieldsNameIterator.next()+"=?,";
	    }
	    sql=sql.substring(0,sql.length()-1)+" ";
	    
	    sql=sql+"where "+primaryKey+"=?";
	    return sql;
	}
	
	
}
