package nju.sec.yz.ExpressSystem.data.carAndDriverdata;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.delete.DeleteHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.find.FindHelper;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.insert.InsertHelper;
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
		FindHelper helper=new FindHelper();
		List<DriverPO> resultList=null;
		try {
			resultList = helper.find(DriverPO.class, "id", "driver", id, "String");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList.get(0);
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
		return null;
	}

	@Override
	public List<DriverPO> findAll(String positionId) throws RemoteException {
		FindHelper helper=new FindHelper();
		List<DriverPO> resultList=null;
		try {
			resultList = helper.find(DriverPO.class, "agency","driver",positionId,"String");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

}
