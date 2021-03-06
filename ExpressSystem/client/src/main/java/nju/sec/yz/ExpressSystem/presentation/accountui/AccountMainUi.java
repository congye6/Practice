package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import nju.sec.yz.ExpressSystem.presentation.componentui.MessageDeclare;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;

public class AccountMainUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
//	private AccountControler controler;
	private String str;
	
	public AccountMainUi(ClientControler mainControler,AccountButtonComponents bc,String str){
		super();
		this.mainControler=mainControler;
//		controler=mainControler.accountControler;
		this.bc=bc;
		this.str=str;
		initAccountUi();
	}
	private void initAccountUi() {
		bc.changePanel(this,0);
		bc.init();
		if(str=="junior"){
			bc.setType("junior");
			bc.changeForJunior();
		}
		setLayout(null);
		setSize(490, 550);
		setVisible(true);
		
		//显示消息列表
		MessageDeclare message=new MessageDeclare(this);
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/main_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}
}
