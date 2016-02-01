package nju.sec.yz.ExpressSystem.data.fileUtility.sql.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.ConnectionHelper;

public class DeleteHelper {

	public int delete(String keyName,String tableName,Object value) throws SQLException{
	    final Connection conn = ConnectionHelper.getConn();
	    int i = 0;
	    String sql = getSQL(keyName, tableName, value);
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	    } finally{
			conn.close();
	    }
	    return i;
	}

	private String getSQL(String keyName, String tableName, Object value) {
		String sql = "delete from "+tableName+" where "+keyName+"='" + value + "'";
		return sql;
	}
	
	
	
	
}
