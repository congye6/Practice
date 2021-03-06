package nju.sec.yz.ExpressSystem.presentation.inventoryui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.componentui.newJBut;
import nju.sec.yz.ExpressSystem.presentation.componentui.newJLabel;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.InventoryControl;
import nju.sec.yz.ExpressSystem.presentation.controlerui.MainControl;

public class InventoryButtonComponents {

	private newJBut in;
	private newJBut out;
	private newJBut checkstock;
	private newJBut observestock;
	private newJBut setAlertRate;
	
	// 退出系统
	private JButton exitButton;
	//退出当前帐户
	private newJLabel leaveButton;
	
	private ClientControler maincontroler;
	private JPanel panel;
	
	
	public InventoryButtonComponents(ClientControler maincontroler,JPanel panel){
		this.panel=panel;
		this.maincontroler=maincontroler;
		
		iniIBC();
		
	}

	private void iniIBC() {
		
//		ImageIcon inIcon = new ImageIcon("graphic/inventory/button/in.png");
		in = new newJBut("入库");
		in.setBounds(13, 50, 108, 41);
		panel.add(in);
		
//		ImageIcon outIcon = new ImageIcon("graphic/inventory/button/out.png");
		out = new newJBut("出库");
		out.setBounds(13, 92, 108, 41);
		panel.add(out);

//		ImageIcon checkIcon = new ImageIcon("graphic/inventory/button/checkstock.png");
		checkstock = new newJBut("库存盘点");
		checkstock.setBounds(13, 134, 108, 41);
		panel.add(checkstock);
		
//		ImageIcon observeIcon = new ImageIcon("graphic/inventory/button/observestock.png");
		observestock = new newJBut("库存查看");
		observestock.setBounds(13, 176, 108, 41);
		panel.add(observestock);
		
//		ImageIcon setIcon = new ImageIcon("graphic/inventory/button/setAlertRate.png");
		setAlertRate = new newJBut("设置警戒值");
		setAlertRate.setBounds(13, 218, 108, 41);
		panel.add(setAlertRate);
		//使得button里的字完全显示 设置边缘为0
		setAlertRate.setMargin(new java.awt.Insets(0,0,0,0)); 


		
		in.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(InventoryControl.INVENTORY_IN);
			}
		});
		out.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(InventoryControl.INVENTORY_OUT);
			}
		});
		checkstock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(InventoryControl.INVENTORY_CHECK);
			}
		});
		observestock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(InventoryControl.INVENTORY_OBSERVE);
			}
		});
		setAlertRate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.inventoryControler.deliverChangePanel(InventoryControl.INVENTORY_SET);
			}
		});
		
		/*
		 * exit
		 */
		
		ImageIcon ExitIcon = new ImageIcon("graphic/common/exit.gif");
		exitButton= new JButton(ExitIcon);
		exitButton.setOpaque(false);
		exitButton.setBorder(null);
		exitButton.setContentAreaFilled(false); 
		exitButton.setBounds(490-19,0,19,19);
		panel.add(exitButton);
		panel.setVisible(true);
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		//离开当前账户
		leaveButton=new newJLabel("注销",Color.white,Color.yellow);
		leaveButton.setBounds(433, 21, 37, 20);
		leaveButton.setVisible(true);
		panel.add(leaveButton);
		leaveButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				maincontroler.mainChangePanel(MainControl.LOGIN);
			}
		});
	
	
		
		
	}
}
