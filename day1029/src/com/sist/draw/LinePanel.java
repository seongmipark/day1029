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
	
	//'��','��','�簢��'�� ����� �׸������� ���� ������� �����
	//��:0   �簢��:1   ��:2
	int drawType;
	
	//'�׸������'�� ���� ���� �߰�
	Color drawColor;
	
	//���� ������� ����Ʈ ����
	ArrayList<GraphicInfo> list;
	
	//�����ڿ��� "���콺�̺�Ʈ"�� ���
	public LinePanel() {
		
		list = new ArrayList<GraphicInfo>();
	
		//this�� �ǹ̴� ���콺�̺�Ʈ�� �߻��Ͽ����� �̺�Ʈ ó�� ��簴ü�� '�ڽ�'�̶�� �ǹ�
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		//System.out.println("�ٽ� �׷��ݴϴ�.");
		//g.drawLine(x1,y1,x2,y2);

		super.paintComponent(g);
		
		//����Ʈ�� ��� ���� �׷��ش�
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
		//�ϳ��� '��'�� �ϼ��ɶ� ����Ʈ ���� ������x,������y,����x,����y�� �����ִ� 
		//GraphicInfo��ü�� ������ ����Ʈ�� ��´�.
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


