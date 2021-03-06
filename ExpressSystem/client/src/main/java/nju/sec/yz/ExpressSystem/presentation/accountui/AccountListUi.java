package nju.sec.yz.ExpressSystem.presentation.accountui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nju.sec.yz.ExpressSystem.bl.accountbl.AccountController;
import nju.sec.yz.ExpressSystem.blservice.accountBlService.AccountBlService;
import nju.sec.yz.ExpressSystem.common.Result;
import nju.sec.yz.ExpressSystem.common.ResultMessage;
import nju.sec.yz.ExpressSystem.presentation.componentui.newTable;
import nju.sec.yz.ExpressSystem.presentation.controlerui.AccountControler;
import nju.sec.yz.ExpressSystem.presentation.controlerui.ClientControler;
import nju.sec.yz.ExpressSystem.vo.AccountVO;

public class AccountListUi extends JPanel{
	private AccountButtonComponents bc;
	private ClientControler mainControler;
	private AccountControler controler;
	private AccountBlService accountBl;
	
	private JTextField JTsearch;
	private JButton JBsearch;
	private newTable table;
	private JButton back;
	private JButton confirm;
	private JLabel warning;
	private Vector<String> name=new Vector<String>();
	private Vector<Vector<String>> data=new Vector<Vector<String>>();
	private List<AccountVO> avl;
	
	private static final int jt_x=224;
	private static final int jt_y=61;
	private static final int jt_w=221;
	private static final int jt_h=22;
	private static final int search_x=444;
	private static final int search_y=61;
	private static final int search_w=22;
	private static final int search_h=21;
	private static final int scroll_x=154;
	private static final int scroll_y=94;
	private static final int scroll_w=315;
	private static final int scroll_h=125;
	private static final int back_x=304;
	private static final int back_y=229;
	private static final int back_w=80;
	private static final int back_h=24;
	private static final int confirm_x=398;
	private static final int confirm_y=229;
	private static final int confirm_w=72;
	private static final int confirm_h=24;
	private static final int warning_x=198;
	private static final int warning_y=490;
	private static final int warning_w=275;
	private static final int warning_h=30;
	
	private ImageIcon searchIcon=new ImageIcon("graphic/account/button/search_button.jpg");
	private ImageIcon backIcon=new ImageIcon("graphic/account/button/back_button.jpg");
	private ImageIcon confirmIcon=new ImageIcon("graphic/account/button/confirm_button.jpg");
	public AccountListUi(ClientControler mainControler,AccountButtonComponents bc){
		super();
		this.mainControler=mainControler;
//		controler=mainControler.accountControler;
		this.bc=bc;
		accountBl=new AccountController();
		avl=accountBl.observeList();
		
		name.add("名称");
		name.add("余额");
		
		table=new newTable(data,name,this,false);
		table.setBounds(scroll_x, scroll_y, scroll_w, scroll_h);
		table.join();
		
		changeData(avl);

		initAccountUi();
	}
//	private 
	private void initAccountUi() {
		bc.changePanel(this);
//		bc.change();
		setLayout(null);
		setSize(490, 550);
		
		JTsearch=new JTextField();
		JTsearch.setBounds(jt_x, jt_y, jt_w, jt_h);
		add(JTsearch);
		JBsearch=new JButton(searchIcon);
		JBsearch.setBounds(search_x,search_y,search_w,search_h);
		JBsearch.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				AccountVO av=accountBl.observeAccount(JTsearch.getText());
				ArrayList<AccountVO> al=new ArrayList<AccountVO>();
				al.add(av);
				if(av!=null)
				{
					changeData(al);
				}
				else
				{
					warning.setText("不存在该名称的账户");
					warning.setVisible(true);
				}
				repaint();
			}
		});
		add(JBsearch);
				
		JBsearch=new JButton(searchIcon);
		JBsearch.setBounds(search_x, search_y, search_w, search_h);
		add(JBsearch);
		
		back=new JButton(backIcon);
		back.setBounds(back_x, back_y, back_w, back_h);
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				List<AccountVO> avo=accountBl.observeList();
				if(avo!=null)
				{
					changeData(avo);
				}
				else
				{
					data.removeAllElements();
					table.resetData();
				
				}
			}
		});
		add(back);
		
		confirm=new JButton(confirmIcon);
		confirm.setBounds(confirm_x, confirm_y, confirm_w, confirm_h);
		confirm.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				ResultMessage result=accountBl.deleteAccount(data.get(table.getSelectedRow()).get(0));
				if(result.getResult()==Result.SUCCESS)
				{
					warning.setText("删除成功");
					avl=accountBl.observeList();//用remove更好？
					changeData(avl);
				}
				else
				{
					warning.setText(result.getMessage());
				}
				warning.setVisible(true);
				repaint();
			}
		});
		add(confirm);
		
		warning=new JLabel();
		warning.setBounds(warning_x, warning_y, warning_w, warning_h);
		warning.setFont(new Font("Dialog", 1, 15));
		warning.setForeground(Color.red);
		add(warning);
		warning.setVisible(false);
		
		setVisible(true);
	}
	private void changeData(List<AccountVO> avo)
	{
		data.removeAllElements();
		for(int i=0;i<avo.size();i++)
		{
			Vector<String> vector=new Vector<String>();
			vector.add(avo.get(i).getName());
			vector.add(new DecimalFormat(".00").format(avo.get(i).getBalance()));
			data.add(vector);
		}
		table.resetData();
	}
	@Override
	public void paintComponent(Graphics g) {

		Image img01 = new ImageIcon("graphic/account/background/delete_background.jpg").getImage();

		g.drawImage(img01, 0, 0, 490, 550, null);

	}

}
