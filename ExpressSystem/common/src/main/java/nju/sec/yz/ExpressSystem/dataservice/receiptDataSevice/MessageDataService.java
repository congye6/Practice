package nju.sec.yz.ExpressSystem.dataservice.receiptDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nju.sec.yz.ExpressSystem.exception.InvalidPasswordException;
import nju.sec.yz.ExpressSystem.po.MessagePO;
/**
 * 保存消息
 * @author 周聪
 *
 */
public interface MessageDataService extends Remote{
	//TODO 查看历史消息
	
	/**
	 * 消息已读后删除
	 * @param messageId
	 * @throws RemoteException
	 */
	public void deleteMessage(String user,String password,String messageId) throws RemoteException,InvalidPasswordException;
	
	public void addMessage(String user,String password,MessagePO po) throws RemoteException,InvalidPasswordException;
	
	/**
	 * 通过当前用户id查找消息
	 * @param personId
	 * @return
	 * @throws RemoteException
	 */
	public List<MessagePO> getMessages(String user,String password,String personId) throws RemoteException,InvalidPasswordException;
	
	
	
}
