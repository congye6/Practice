package nju.sec.yz.ExpressSystem.dataservice.manageDataSevice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.po.StaffPO;

/**
 * 
 * @author zhangqi
 *
 */
public interface StaffDataService extends Remote{
	public ResultMessage insert(StaffPO spo) throws RemoteException;
	public StaffPO find(String id) throws RemoteException;
	public ResultMessage delete(String id) throws RemoteException;
	public ResultMessage init(List<StaffPO> staffs) throws RemoteException;
	public ArrayList<StaffPO> findAll( ) throws RemoteException;
	public ResultMessage update(String loginId, StaffPO po) throws RemoteException;
}
