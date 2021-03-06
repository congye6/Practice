package nju.sec.yz.ExpressSystem.bl.accountbl;

import java.util.List;

import nju.sec.yz.ExpressSystem.bl.tool.LogTool;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.FinanceBlSevice;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.BussinessVO;
import nju.sec.yz.ExpressSystem.vo.OutVO;
import nju.sec.yz.ExpressSystem.vo.PaymentSheetVO;
import nju.sec.yz.ExpressSystem.vo.PaymentVO;
import nju.sec.yz.ExpressSystem.vo.ProfitVO;
/**
 * 负责财务进出的逻辑控制
 * @author 周聪
 *
 */
public class FinanceController implements FinanceBlSevice{

	@Override
	public BussinessVO checkBusinessCircumstance(String begin, String end) {
		Finance finance=new Finance();
		BussinessVO vo=finance.checkBusinessCircumstance(begin, end);
		LogTool.setLog("制定经营情况表");
		return vo;
	}

	@Override
	public PaymentVO checkReceipt(String day, String positionId) {
		Collection in=new Collection();
		
		return in.getByPosition(day, positionId);
	}

	@Override
	/**
	 * 制定收款单
	 */
	public ResultMessage makeReceipt(PaymentSheetVO psv) {
		Collection collection=new Collection();
		ResultMessage message=collection.make(psv);
		LogTool.setLog("制定收款单");
		return message;
	}

	@Override
	public ResultMessage makePayment(OutVO pro) {
		Payment receipt=new Payment();
		ResultMessage messsge=receipt.make(pro);
		LogTool.setLog("制定付款单");
		return messsge;
	}

	@Override
	public ProfitVO makeCostReceipt() {
		Finance finance=new Finance();
		ProfitVO vo=finance.makeCostReceipt();
		return vo;
		
	}

	@Override
	public ResultMessage exportCostToExcel(ProfitVO rv) {
		Finance finance=new Finance();
		ResultMessage message=finance.exportCostToExcel(rv);
		return message;
	}

	@Override
	public ResultMessage exportBussinessToExcel(BussinessVO vo) {
		Finance finance=new Finance();
		ResultMessage message=finance.exportBussinessToExcel(vo);
		return message;
	}
	
	

}
