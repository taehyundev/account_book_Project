package app;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private Image img;
	public ImagePanel(Image img) {
		// TODO Auto-generated constructor stub
		this.img = img;
		//기본 사이즈
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		//편한 사이즈
		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setLayout(null);
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
	}
	public Dimension getDim() {
		return new Dimension(img.getWidth(null),img.getHeight(null)+50);
	}
}
