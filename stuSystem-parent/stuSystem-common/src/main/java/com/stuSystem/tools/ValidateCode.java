package com.stuSystem.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * 验证码生成类
 */
public class ValidateCode {
	private int w = 70;
	private int h = 35;
	private Random r = new Random();
	private String codes = "0123456789";
	private Color bgColor = new Color(255,255,255);
	private String text;
	
	private Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red,green,blue);
	}	
	private Font randomFont() {
		int style = r.nextInt(4);
		int size = r.nextInt(5)+21;
		return new Font("黑体",style,size);
	}
	private char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}
	private void drawLine(BufferedImage img) {
		int num = 3;
		Graphics2D g2 = (Graphics2D)img.getGraphics();
		for(int i=0;i<num;i++) {
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(h);
			g2.setStroke(new BasicStroke(1.5f));
			g2.drawLine(x1, y1, x2, y2);
		}
	}
	private BufferedImage createImg() {
		BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D)img.getGraphics();
		g2.setColor(this.bgColor);
		g2.fillRect(0, 0, w, h);
		return img;
	}
	public BufferedImage getImg() {
		BufferedImage img = this.createImg();
		Graphics2D g2 = (Graphics2D)img.getGraphics();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<4;i++) {
			String s = this.randomChar()+"";
			sb.append(s);
			float x = w/4*1.0f*i;
			g2.setFont(this.randomFont());
			g2.setColor(new Color(0,0,0));
			g2.drawString(s, x, h-5);
		}
		this.text = sb.toString();
		this.drawLine(img);
		return img;
	}
	public String getText() {
		return this.text;
	}
	public static void output(BufferedImage img,OutputStream out) throws IOException{
		ImageIO.write(img, "JPEG", out);
	}
	/*public static void main(String args) throws FileNotFoundException, IOException {
		ValidateCode vc = new ValidateCode();
		BufferedImage img = vc.getImg();
		String text = vc.getText();
		ImageIO.write(img, "JPEG", new FileOutputStream(new File("D:\\img\\a.jpg")));
	}*/
}