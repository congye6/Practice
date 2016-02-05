package nju.sec.yz.ExpressSystem.server;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.Test;

import nju.sec.yz.ExpressSystem.data.carAndDriverdata.DriverDataBaseImpl;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;

public class FindTester {

	@Test
	public void test() {
		DriverDataService data=new DriverDataBaseImpl();
		try {
			List<DriverPO> list=data.findAll("025001");
			System.out.println(list.size());
			System.out.println(list.get(1).getBirthDate());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
