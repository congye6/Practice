package nju.sec.yz.ExpressSystem.bl.deliverbl;

import javax.sound.midi.Receiver;

import nju.sec.yz.ExpressSystem.bl.object_driver.DeliverDriver;
import nju.sec.yz.ExpressSystem.bl.userbl.User;
import nju.sec.yz.ExpressSystem.bl.userbl.UserInfo;
import nju.sec.yz.ExpressSystem.common.DeliveryState;
import nju.sec.yz.ExpressSystem.common.ReceiveInformation;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.DeliverStateVO;
import nju.sec.yz.ExpressSystem.vo.ReceiveVO;

/**
 * 收件单的领域模型
 * 收件单不用审批
 * @author 周聪
 *
 */
public class RecieveReceipt {


	public ResultMessage make(ReceiveVO vo) {
		ReceiveInformation info=vo.getReceiveInformation();
		String validResult=isValid(vo);
		if(!validResult.equals("success")){
			return new ResultMessage(Result.FAIL,validResult);
		}
			
		Deliver deliver=new Deliver();
		//更新历史轨迹
		ResultMessage message = deliver.updateDeliverInfo(info.getId(), 
				"您的快递已签收，期待再次为您服务 "+info.getTime(), DeliveryState.RECEICE,"");
		return message;
	}
	
	private String getDeliverId(){
		UserInfo user=new User();
		return user.getCurrentID();
	}
	
	private String isValid(ReceiveVO vo){
		Deliver deliver=new Deliver();
		
		String barID=vo.getReceiveInformation().getId();
		String date=vo.getReceiveInformation().getTime();
		if(!ValidHelper.isBarId(barID))
			return "亲，咱们的订单号是十位数字哟~";
		
		
		
		
		DeliverStateVO state=deliver.getDeliverState(barID);
		
		if(state==null||state.nextAgency==null)
			return "亲，系统中不存在订单"+barID;
		
		if(state.state!=DeliveryState.DELIVING)
			return "亲，这订单还没开始派送哦~";
		
		if(!this.getDeliverId().equals(state.nextAgency))
			return "亲，这订单不是你派送的哦~";

		if(!ValidHelper.isBeforeDate(date))
			return "日期是不是输错了~";

		return "success";
	}


	
}
