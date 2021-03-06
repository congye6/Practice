package nju.sec.yz.ExpressSystem.presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.managerbl.ManagerController;
import nju.sec.yz.ExpressSystem.blservice.managerBlService.StaffBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.common.Status;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJCombo;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJText;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.PositionVO;
import nju.sec.yz.ExpressSystem.vo.StaffVO;
import nju.sec.yz.ExpressSystem.vo.TransitVO;

public class ManagerStaffAdd extends JPanel{
	private StaffBlService manager = new ManagerController();
	private ClientControler maincontroler;
	private ManagerButtonComponent mbc;

	// 姓名
	private newJText name;

	// 人员编号
	private newJText id;

	// 职位，权限
	private newJCombo power;

	// 所属机构
	private newJCombo agency;
	
//	private newJText logId;
	
	private newJBut confirmButton;
	private newJLabel warning=new newJLabel();

	public ManagerStaffAdd(ClientControler maincontroler,ManagerButtonComponent mbc) {
		this.maincontroler=maincontroler;
		this.mbc=mbc;
		mbc.changePanel(this);
//		mbc.change();
		iniManagerStaffAdd();
	}

	private void iniManagerStaffAdd() {
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		name=new newJText();
		name.setBounds(193, 72, 74, 18);
		add(name);
		
		id=new newJText();
		id.setBounds(218, 102, 83, 18);
		add(id);
		
//		logId=new newJText();
//		logId.setBounds(218, 191, 83, 18);
//		add(logId);
		
		String[] status={
				"快递员",
				"营业厅业务员",
				"中转中心工作人员",
				"仓库管理人员",
				"初级财务人员",
				"高级财务人员",
				"总经理",
				"管理员"
		};
		power=new newJCombo(status);
		power.setBounds(187,131,116,20);
		add(power);
		
		
		ArrayList<String> agencyId=new ArrayList<String>();
		ArrayList<TransitVO> trans=((ManagerController) manager).observeAllTransit();
		int count=0;
		for(int i=0;i<trans.size();i++){
			TransitVO temptra=trans.get(i);	
			ArrayList<PositionVO> pots=(ArrayList<PositionVO>) temptra.getPositions();
			agencyId.add(temptra.getName());
			count++;
			for(int t=0;t<pots.size();t++){
				agencyId.add(pots.get(t).getName());
				count++;
			}
		}
		
		String[] s=new String[count];
		agency=new newJCombo(agencyId.toArray(s));
		agency.addItem("总公司");
		agency.setBounds(218,160,110,20);
		add(agency);
		
		

		/*
		 * 确定
		 */
//		ImageIcon cinfirmIcon = new ImageIcon("graphic/deliver/button/confirm.png");
		confirmButton = new newJBut("确定");
		confirmButton.setBounds(378, 256, 72, 24);
		add(confirmButton);
		setVisible(true);

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 判断必填项是否填写完成
				if ((name.getText().equals("")) || (id.getText().equals(""))) {
					warning.NotFilled();
					
				} else {
					// translate data
					StaffVO vo=new StaffVO(name.getText(),id.getText(),getstatus(power),agency.getSelectedItem().toString());
					// 判断输入的信息是否正确
					ResultMessage result = manager.addStaff(vo);
					// 失败
					warning.Reply(result);
					add(warning);
					repaint();
				}
			}
		});
	}
	
	
	public static Status getstatus(JComboBox powerType) {
		switch (powerType.getSelectedItem().toString()) {
		case "快递员":
			return Status.DELIVER;
		case "营业厅业务员":
			return Status.POSITION;
		case "中转中心工作人员":
			return Status.TRANSIT;
		case "初级财务人员":
			return Status.JUNIOR_ACCOUNTANCY;
		case "高级财务人员":
			return Status.SENIOR_ACCOUNTANCY;
		case "总经理":
			return Status.MANAGER;
		case "仓库管理人员":
			return Status.INVENTORY;
		case "管理员":
			return Status.ADMINISTRATOR;
		default:
			return null;

		}
	}


	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/manager/background/background07.png").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
