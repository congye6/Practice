package nju.sec.yz.ExpressSystem.bl.stub;
/**
 * rename
 */
import java.util.ArrayList;

import nju.sec.yz.ExpressSystem.blservice.userBlService.UserBlService;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.vo.UserVO;

public class UserBlStub implements UserBlService {

	@Override
	public ResultMessage login(String id, String password) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ArrayList<UserVO> getAll() {
		UserVO uvo1 = new UserVO("s001","刘强东","jingdong",Status.MANAGER);
		UserVO uvo2 = new UserVO("s002","章泽天","jingdong",Status.DELIVER);
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list.add(uvo1);
		list.add(uvo2);
		return  list;
	}

	@Override
	public UserVO getSingle(String id) {
		// TODO 自动生成的方法存根
		return new UserVO("s001","刘强东","jingdong",Status.MANAGER);
	}

	@Override
	public ResultMessage add(UserVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage del(String id) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public ResultMessage modify(UserVO vo) {
		// TODO 自动生成的方法存根
		return null;
	}

}
