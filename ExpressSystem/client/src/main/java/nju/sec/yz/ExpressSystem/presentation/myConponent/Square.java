package nju.sec.yz.ExpressSystem.presentation.myConponent;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import twaver.TWaverUtil;

public class Square {

	public void draw(Graphics2D g2d, int x, int y, int size) {
		Color color = Color.black;
		RoundRectangle2D body = new RoundRectangle2D.Float(x, y, size, size, size / 3, size / 3);

		// draw body
		g2d.setColor(color);
		GradientPaint paint = new GradientPaint(x, y, color.darker(), x, y + size, color.brighter().brighter());
		g2d.setPaint(paint);
		g2d.fill(body);

		// draw image
		g2d.setClip(body);
		Image image = new ImageIcon("graphics/1.png").getImage();
		g2d.drawImage(image, x + (size - image.getWidth(null)) / 2, y + (size - image.getHeight(null)) / 2, null);
		g2d.setClip(null);

		// draw highlight.
		Shape highlightArea = createHighlightShape(x, y, size, body);
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.fill(highlightArea);

		// draw outline.
		g2d.setColor(color.darkGray);
		g2d.draw(body);

	}

	private Shape createHighlightShape(int centerX, int centerY, int size, Shape body) {
		double myRadius = size * 4;
		double x = centerX - size * 2.3;
		double y = centerY - size * 3.2;
		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, myRadius, myRadius);
		Area area = new Area(circle);
		area.intersect(new Area(body));
		return area;
	}
	
	public Image createImageIcon(Image phantom, int size) {
		BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		

		draw(g2d, 0, 0, size);

		g2d.dispose();
		return bi;
	}
}
