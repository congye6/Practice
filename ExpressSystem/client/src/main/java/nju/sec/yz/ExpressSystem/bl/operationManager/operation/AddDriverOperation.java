package nju.sec.yz.ExpressSystem.bl.operationManager.operation;

import nju.sec.yz.ExpressSystem.bl.carAndDriverbl.DriverController;
import nju.sec.yz.ExpressSystem.bl.operationManager.UndoableOperation;
import nju.sec.yz.ExpressSystem.blservice.carAndDriverBlService.DriverBlService;
import nju.sec.yz.ExpressSystem.vo.DriverVO;

public class AddDriverOperation extends UndoableOperation{

	private DriverVO vo;
	
	public AddDriverOperation(DriverVO vo) {
		super();
		this.vo = vo;
	}

	@Override
	protected void command() {
		DriverBlService bl=new DriverController();
		bl.add(vo);
	}

	@Override
	protected void restore() {
		DriverBlService bl=new DriverController();
		bl.del(vo.getId());
	}

}
