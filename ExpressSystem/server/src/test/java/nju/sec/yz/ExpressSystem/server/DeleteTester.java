package nju.sec.yz.ExpressSystem.server;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import nju.sec.yz.ExpressSystem.data.carAndDriverdata.DriverDataBaseImpl;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;

public class DeleteTester {

	@Test
	public void test() {
		DriverDataService data=new DriverDataBaseImpl();
		try {
			data.delete("025001B003");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
