package nju.sec.yz.ExpressSystem.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.junit.Test;

import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.data.carAndDriverdata.DriverDataBaseImpl;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;

public class InsertSQLTester {

	@Test
	public void test(){
		DriverDataService data = new DriverDataBaseImpl();
		try {
//			data.insert(new DriverPO("025001B004", "hh", "19560312", "4378324623482348934", "1234455666", Sex.FEMALE,
//					"025001", "20170213"));

			DriverPO po = data.find("025001B004");
			assertEquals(Sex.FEMALE, po.getSex());

		} catch (RemoteException e) {
			e.printStackTrace();
		} 
	}
	
	
		
	
	
}
