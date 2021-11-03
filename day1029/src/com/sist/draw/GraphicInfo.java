package com.sist.draw;

import java.io.Serializable;
import java.awt.Color;

//'선'하나를 포장하기 위한 클래스 만든다
public class GraphicInfo implements Serializable {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int drawtype;
	private Color drawcolor;


	public GraphicInfo(int x1, int y1, int x2, int y2, int drawtype, Color drawcolor) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.drawtype = drawtype;
		this.drawcolor = drawcolor;
	}

	public GraphicInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getDrawtype() {
		return drawtype;
	}

	public void setDrawtype(int drawtype) {
		this.drawtype = drawtype;
	}

	public Color getDrawcolor() {
		return drawcolor;
	}

	public void setDrawcolor(Color drawcolor) {
		this.drawcolor = drawcolor;
	}
	
	
}
