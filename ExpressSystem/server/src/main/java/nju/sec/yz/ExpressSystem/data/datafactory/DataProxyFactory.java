package nju.sec.yz.ExpressSystem.data.datafactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import nju.sec.yz.ExpressSystem.data.mock_object.MessageDataStub;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountBookDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.AccountDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.InDataService;
import nju.sec.yz.ExpressSystem.dataservice.accountDataSevice.OutDataService;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.CarDataService;
import nju.sec.yz.ExpressSystem.dataservice.carAndDriverDataSevice.DriverDataService;
import nju.sec.yz.ExpressSystem.dataservice.datafactory.DatafactoryService;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.BarIdsDataService;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.CollectionRecordDataService;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.DeliverDataService;
import nju.sec.yz.ExpressSystem.dataservice.deliverDataSevice.OrderDataService;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryDataService;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryInDataService;
import nju.sec.yz.ExpressSystem.dataservice.inventoryDataSevice.InventoryOutDataService;
import nju.sec.yz.ExpressSystem.dataservice.logDataSevice.LogDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.AgencyDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.CityIdDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.ConstDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.SalaryDataService;
import nju.sec.yz.ExpressSystem.dataservice.manageDataSevice.StaffDataService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.MessageDataService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptCounterDataService;
import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.ReceiptDataService;
import nju.sec.yz.ExpressSystem.dataservice.userDataSevice.UserDataService;
import nju.sec.yz.ExpressSystem.proxy.PasswordInterceptor;
import nju.sec.yz.ExpressSystem.proxy.Proxy;

public class DataProxyFactory extends UnicastRemoteObject implements DatafactoryService{

	private Proxy proxy;
	
	public DataProxyFactory() throws RemoteException{
		proxy=new Proxy(new PasswordInterceptor());
	}
	
	
	@Override
	public void ping() throws RemoteException {
		
		
	}

	@Override
	public MessageDataService getMessageDataService() throws RemoteException {
		return (MessageDataService)proxy.bind(new MessageDataStub());
	}

	@Override
	public BarIdsDataService getBarIdsDataService() throws RemoteException {
		return null;
	}

	@Override
	public CollectionRecordDataService getCollectionRecordDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityIdDataService getCityIdDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDataService getOrderDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptCounterDataService getCounterDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeliverDataService getDeliverDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBookDataService getAccountBookDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDataService getAccountDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InDataService getInDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutDataService getOutDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarDataService getCarDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverDataService getDriverDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryDataService getInventoryDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryInDataService getInventoryInDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryOutDataService getInventoryOutDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogDataService getLogDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AgencyDataService getAgencyDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstDataService getConstDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalaryDataService getSalaryDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StaffDataService getStaffDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReceiptDataService getReceiptDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDataService getUserDataService() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
