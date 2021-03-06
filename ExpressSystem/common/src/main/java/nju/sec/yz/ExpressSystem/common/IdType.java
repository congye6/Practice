package nju.sec.yz.ExpressSystem.common;
/**
 * 各种编号
 * @author 周聪
 *
 */
public enum IdType {
	COLLECTION("k",3){
		//收款单
	},
	PAYMENT("f",3){
		//付款单
	},
	DELIVER_RECEIPT("j",5){
		//寄件单
	},
	POSITION_LOADING_RECEIPT("yz",3){
		
	},
	POSITION_RECEIVE_RECEIPT("yd",5){
		
	},
	POSITION_SEND_RECEIPT("p",3){
		
	},
	TRANSIT_RECEIPT("z",5){
		
	},
	TRANSIT_RECEIVE_RECEIPT("zd",5){
		
	},
	TRANSIT_LOADING_RECEIPT("zz",3){
		
	},
	INVENTORY_IN("r",5){
		
	},
	INVENTORY_OUT("c",5){
		
	},POSITION_TRANSPORT("",5){
		//营业厅汽运编号
	},TRANSIT_CAR_TRANSPORT("",7){
		//中转中心汽运编号
	},TRANSIT_TRAIN_TRANSPORT("",7){
		//中转中心货运编号
	},TRANSIT_FLIGHT_TRANSPORT("",7){
		//中转中心航运编号
	},MESSAGE("m",5){
		//消息id
	},INITIAL("i",3){
		//期初建帐id
	};
	
	private IdType(String idStr,int length){
		this.idStr=idStr;
		this.length=length;
	}
	
	/**
	 * 生成表单id时要加的字符
	 */
	private String idStr;
	
	/**
	 * 表单的后几位编号的长度
	 */
	private int length;
	
	public String getIdStr() {
		return idStr;
	}

	public int getLength() {
		return length;
	}
}
