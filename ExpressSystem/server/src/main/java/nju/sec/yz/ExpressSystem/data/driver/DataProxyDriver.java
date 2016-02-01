package nju.sec.yz.ExpressSystem.data.driver;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import nju.sec.yz.ExpressSystem.data.datafactory.DataProxyFactory;
import nju.sec.yz.ExpressSystem.dataservice.datafactory.DatafactoryService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.MessageDataService;
import nju.sec.yz.ExpressSystem.exception.InvalidPasswordException;

public class DataProxyDriver {

	private DatafactoryService factory;
	
	public DataProxyDriver() {
		try {
			factory=new DataProxyFactory();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void messageData() {
		try {
			MessageDataService messageData=factory.getMessageDataService();
			messageData.deleteMessage("hhh", "hh", "123");
		} catch (RemoteException | InvalidPasswordException e) {
			assertEquals("Invalid Password", e.getMessage());
			return;
		}
		assertTrue(false);
		
	}
	
	@Test
	public void messageData2() {
		try {
			MessageDataService messageData=factory.getMessageDataService();
			messageData.deleteMessage("hh", "hh", "123");
		} catch (RemoteException | InvalidPasswordException e) {
			assertTrue(false);
			return;
		}
		
		
	}

}
