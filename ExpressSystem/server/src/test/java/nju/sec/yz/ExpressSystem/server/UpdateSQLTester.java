package nju.sec.yz.ExpressSystem.server;

import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import nju.sec.yz.ExpressSystem.common.Sex;
import nju.sec.yz.ExpressSystem.data.carAndDriverdata.DriverDataBaseImpl;
import nju.sec.yz.ExpressSystem.data.fileUtility.sql.uodate.UpdateSQLBuilder;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.po.DriverPO;

public class UpdateSQLTester {

	@Test
	public void testSQL() {
		List<String> list=new ArrayList<>();
		list.add("po");
		list.add("id");
		assertEquals("update driver set po=?,id=? where name=?",new UpdateSQLBuilder().getSQL("driver","name",list.iterator()));
	}
	
	@Test
	public void testUpdate(){
		DriverDataService data=new DriverDataBaseImpl();
		try {
			data.update(new DriverPO("025001B004", "jj", "19560312", "4378324623482348934", "1234455666", Sex.FEMALE,
					"025001", "20170213"));
			DriverPO po = data.find("025001B004");
			assertEquals(Sex.FEMALE, po.getSex());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
