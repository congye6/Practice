package nju.sec.yz.ExpressSystem.data.carAndDriverdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.ConnectionHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.SerializetHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.delete.DeleteHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.insert.InsertHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.insert.InsertSQLBuilder;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.uodate.UpdateHelper;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;

public class DriverDataBaseImpl implements DriverDataService{

	private static final String TABLE_NAME="driver";
	
	private static final String PRIMARY_KEY="id";
	
	@Override
	public ResultMessage insert(DriverPO dpo) throws RemoteException {
		
		InsertHelper helper=new InsertHelper();
		
		try {
			helper.insert(dpo,TABLE_NAME);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public DriverPO find(String id) throws RemoteException {
		String sql="select * from driver where id='"+id+"'";
		DriverPO result=null;
		try {
			Connection conn=ConnectionHelper.getConn();
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet resultSet=pst.executeQuery();
			if(resultSet.next()){
				result=new DriverPO();
				result.setAgency(resultSet.getString("agency"));
				result.setBirthDate(resultSet.getString("birthDate"));
				result.setSex((Sex)SerializetHelper.deSerialize(resultSet.getBlob("sex")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		DeleteHelper helper=new DeleteHelper();
		try {
			helper.delete(PRIMARY_KEY, TABLE_NAME, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ResultMessage update(DriverPO dpo) throws RemoteException {
		UpdateHelper helper=new UpdateHelper();
		try {
			helper.update(dpo,TABLE_NAME,PRIMARY_KEY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResultMessage(Result.SUCCESS);
	}

	@Override
	public ResultMessage init(List<DriverPO> drivers) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DriverPO> findAll(String positionId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
