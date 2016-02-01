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
import nju.sec.yz.ExpressSystem.data.datafactory.ConnectionHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.InsertHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.InsertObjectHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.InsertSQLBuilder;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;

public class DriverDataBaseImpl implements DriverDataService{

	@Override
	public ResultMessage insert(DriverPO dpo) throws RemoteException {
		
		InsertHelper helper=new InsertHelper();
		
		try {
			helper.insert(dpo);
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
				result.setSex((Sex)InsertObjectHelper.deSerialize(resultSet.getBlob("sex")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public ResultMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage update(DriverPO dpo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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
