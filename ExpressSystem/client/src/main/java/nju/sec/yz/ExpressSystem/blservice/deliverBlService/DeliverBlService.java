package nju.sec.yz.ExpressSystem.blservice.deliverBlService;

import java.util.List;

import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.BarIdsVO;
import nju.sec.yz.ExpressSystem.vo.CollectionRecordVO;
import nju.sec.yz.ExpressSystem.vo.DeliverVO;
import nju.sec.yz.ExpressSystem.vo.DeliverySheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.OfficeLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;
import nju.sec.yz.ExpressSystem.vo.SendSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitArriveSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitLoadSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitSheetVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

/**
 * @author xiaosaisai
 * @change YU Fan 
 * 物流接口
 */
public interface DeliverBlService {
	/**
	 * 物流历史轨迹
	 */
	public DeliverVO  checkDeliver(String id);
	
	/**
	 * 到达单输入中转单编号获得条形码号列表
	 * 前置条件：制定到达单时填写的中转单号目的地为当前机构
	 */
	public BarIdsVO getBarIdList(String transitSheetId);
	
	/**
	 * 快递员查询订单(寄件单)信息
	 */
	public SendSheetVO checkDeliverReceipt(String barID);
	
	/**
	 * 获得当前用户所在的中转中心
	 */
	public TransitVO getCurrentTransit();
	
	/**
	 * 获得当前用户所在的营业厅
	 */
	public PositionVO getCurrentPosition();
	
	/**
	 * 营业厅装车单和到达单可选机构
	 */
	public List<String> getValidAgency();
	
	/**
	 * 营业厅制定收款单时可选的账户列表名称
	 */
	public List<String> getAccounts();
	
	/**
	 * 获得当前营业厅的收款记录
	 */
	public List<CollectionRecordVO> getCollectionRecords();
	
	/**
	 * 寄件单
	 * @param vo
	 * @return
	 */
	public ResultMessage deliverReceipt (SendSheetVO vo);
	/**
	 * 收件单
	 * @param vo
	 * @return
	 */
	public ResultMessage  recieveReceipt(ReceiveVO vo);
	/**
	 * 营业厅装车单
	 * @param vo
	 * @return
	 */
	public ResultMessage positionLoadingReceipt (OfficeLoadSheetVO vo);
	/**
	 * 营业厅到达单
	 * @param vo
	 * @return
	 */
	public ResultMessage positionReceiveReceipt(OfficeArriveSheetVO vo);
	/**
	 * 营业厅派件单
	 * @param vo
	 * @return
	 */
	public ResultMessage positionSendReceipt(DeliverySheetVO vo);
	/**
	 * 中转中心到达单
	 * @param vo
	 * @return
	 */
	public ResultMessage transitReceiveReceipt(TransitArriveSheetVO vo);
	//中转中心装车单
	public ResultMessage transitLoadingReceipt (TransitLoadSheetVO vo);
	
	/**
	 * 三种中转单
	 */
	public ResultMessage transitFlightReceipt(TransitSheetVO vo);
	
	public ResultMessage transitTrainReceipt(TransitSheetVO vo);
	
	public ResultMessage transitCarReceipt(TransitSheetVO vo);
	
}

