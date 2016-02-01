package nju.sec.yz.ExpressSystem.presentation.myConponent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyButton {

	
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		Square s=new Square();
		JButton button=new JButton(new ImageIcon(s.createImageIcon(null, 50)));
		button.setBounds(25, 0, 50, 50);
		frame.add(button);
		frame.setLocationRelativeTo(null);
		frame.setSize(1000, 100);
		frame.setVisible(true);
	}
	
	
}
