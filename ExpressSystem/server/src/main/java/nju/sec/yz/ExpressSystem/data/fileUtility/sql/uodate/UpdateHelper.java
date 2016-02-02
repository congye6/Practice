package nju.sec.yz.ExpressSystem.data.fileUtility.sql.uodate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import nju.sec.yz.ExpressSystem.data.fileUtility.sql.ConnectionHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.FieldsInfoTool;

public class UpdateHelper {

	public <T> void update(T entity,String tableName,String primaryKey) throws SQLException{
		Connection conn=ConnectionHelper.getConn();
		UpdateSQLBuilder builder=new UpdateSQLBuilder();
		List<String> fieldsNameList=FieldsInfoTool.getFieldsName(entity);
		fieldsNameList.remove(primaryKey);
		String sql=builder.getSQL(tableName, primaryKey,fieldsNameList.iterator());
		PreparedStatement pst=conn.prepareStatement(sql);
	}
	
}
