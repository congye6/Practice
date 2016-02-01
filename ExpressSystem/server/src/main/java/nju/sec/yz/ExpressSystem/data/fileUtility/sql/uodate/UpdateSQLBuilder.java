package nju.sec.yz.ExpressSystem.data.fileUtility.sql.uodate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdateSQLBuilder {

	public String getSQL(String tableName,String primaryKey,Iterator<String> fieldsNameIterator) {
	    
	    String sql = "update "+tableName+" ";
	    
	    while(fieldsNameIterator.hasNext()){
	    	sql+="set "+fieldsNameIterator.next()+"=?,";
	    }
	    sql=sql.substring(0,sql.length()-1)+" ";
	    
	    sql=sql+"where "+primaryKey+"=?";
	    return sql;
	}
	
	
}
