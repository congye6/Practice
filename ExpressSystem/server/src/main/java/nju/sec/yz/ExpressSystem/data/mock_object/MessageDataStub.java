package nju.sec.yz.ExpressSystem.data.mock_object;

import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice.MessageDataService;
import nju.sec.yz.ExpressSystem.exception.InvalidPasswordException;
import nju.sec.yz.ExpressSystem.po.MessagePO;

public class MessageDataStub implements MessageDataService{

	@Override
	public void deleteMessage(String user, String password, String messageId)
			throws RemoteException, InvalidPasswordException {
		
	}

	@Override
	public void addMessage(String user, String password, MessagePO po)
			throws RemoteException, InvalidPasswordException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MessagePO> getMessages(String user, String password, String personId)
			throws RemoteException, InvalidPasswordException {
		// TODO Auto-generated method stub
		return null;
	}

}
