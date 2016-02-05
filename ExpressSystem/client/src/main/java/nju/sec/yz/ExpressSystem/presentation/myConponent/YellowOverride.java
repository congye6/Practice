package nju.sec.yz.ExpressSystem.presentation.myConponent;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class YellowOverride implements MouseEnterAction{

	@Override
	public void paint(int weight,int height,Graphics2D g2d){ 
		BufferedImage image = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB);
		// 获取Graphics2D
		Graphics2D pen = image.createGraphics();
		//背景透明
		image = pen.getDeviceConfiguration().createCompatibleImage(weight, height, Transparency.TRANSLUCENT);
		pen.dispose();
		pen=image.createGraphics();
		
		pen.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2F));
		pen.drawImage(new ImageIcon("graphic/RMI/1.gif").getImage(), 0,0,weight,height, null);
		
		g2d.drawImage(image, 0, 0, null);
	}
	
}
