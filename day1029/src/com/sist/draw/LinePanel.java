package com.sist.draw;

import java.awt.Graphics;
import java.awt.Color;

import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.event.MouseEvent;

public class LinePanel extends JPanel implements MouseListener {
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	
	//'선','원','사각형'중 어떤것을 그릴건지를 위한 멤버변수 만들기
	//선:0   사각형:1   원:2
	int drawType;
	
	//'그리기색상'을 위한 변수 추가
	Color drawColor;
	
	//선을 담기위한 리스트 선언
	ArrayList<GraphicInfo> list;
	
	//생성자에서 "마우스이벤트"를 등록
	public LinePanel() {
		
		list = new ArrayList<GraphicInfo>();
	
		//this의 의미는 마우스이벤트가 발생하였을때 이벤트 처리 담당객체가 '자신'이라는 의미
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		//System.out.println("다시 그려줍니다.");
		//g.drawLine(x1,y1,x2,y2);

		super.paintComponent(g);
		
		//리스트에 담긴 선을 그려준다
		for( GraphicInfo info : list ) {
		x1 = info.getX1();
		y1 = info.getY1();			
		x2 = info.getX2();
		y2 = info.getY2();
		int width = x2 - x1;
		int height = y2 - y1;
		g.setColor(info.getDrawcolor());
		
		switch(info.getDrawtype()) {
		case 0: g.drawLine(x1,y1,x2,y2); break;
		case 1: g.drawRect(x1,y1,width,height); break;
		case 2:	g.drawOval(x1,y1,width,height); break;
		}		
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x1 = e.getX();
		y1 = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// TODO Auto-generated method stub
		x2 = e.getX();
		y2 = e.getY();		
		//하나의 '선'이 완성될때 리스트 선의 시작점x,시작점y,끝점x,끝점y를 갖고있는 
		//GraphicInfo객체를 생성해 리스트에 담는다.
		list.add(new GraphicInfo(x1, y1, x2, y2, drawType, drawColor));
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}


