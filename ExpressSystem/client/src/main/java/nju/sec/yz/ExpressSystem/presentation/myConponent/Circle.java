package nju.sec.yz.ExpressSystem.presentation.myConponent;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import twaver.TWaverUtil;

public class Circle {
	static public void draw(Graphics2D g2d, int radius, int centerX, int centerY) {
		
		Color color = TWaverUtil.getRandomColor();

		Ellipse2D.Double circle = new Ellipse2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2);

		// draw body
		g2d.setColor(color);
		GradientPaint paint = new GradientPaint(centerX, centerY, color, centerX, centerY + radius * 2,
				color.brighter().brighter());
		g2d.setPaint(paint);
		g2d.fill(circle);

		// draw highlight.
		Shape highlightArea = createHighlightShape(centerX, centerY, radius);
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.fill(highlightArea);

		// draw image
		g2d.setClip(circle);
		Image image = new ImageIcon("graphic/RMI/1.jpg").getImage();
		g2d.drawImage(image, centerX - image.getWidth(null) / 2, centerY - image.getHeight(null) / 2, null);
		g2d.setClip(null);

	}

	private static Shape createHighlightShape(int centerX, int centerY, int radius) {
		double myRadius = radius * 0.8;
		double x = centerX - myRadius;
		double y1 = centerY - myRadius - myRadius / 5;
		double y2 = centerY - myRadius - myRadius / 5 * 2;

		Ellipse2D.Double circle1 = new Ellipse2D.Double(x, y1, myRadius * 2, myRadius * 2);
		Ellipse2D.Double circle2 = new Ellipse2D.Double(x, y2, myRadius * 2, myRadius * 2);

		Area area = new Area(circle1);
		area.intersect(new Area(circle2));
		return area;
	}

	public static Image createImageIcon(Image phantom, int size) {
		BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int center = size / 2;
		int radius = center;

		draw(g2d, radius, center, center);

		g2d.dispose();
		return bi;
	}
}
