package com.sist.draw;


import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.Color;

public class MyFrame extends JFrame implements ActionListener {
	private LinePanel lp;
	
	//������ ���ϸ�� ����� ���ϸ��� ������ �� �ֵ��� �ϴ� JFileChooser�� ��������� ����
	JFileChooser jfc;
	
	//������ �����ϱ� ���� ���̾�α� JColorChooser�� ��������� ����
	JColorChooser jcc;
	
	public MyFrame() {
		lp = new LinePanel();
		add(lp);
		
		//JFileChooser ��ü�� ����
		jfc = new JFileChooser("c:/myData");
		
		
		//JColorChooser ��ü ����
		jcc = new JColorChooser();
		
		//�޴��ٸ� �����մϴ�.
		JMenuBar jmb = new JMenuBar();
		
		//�ָ޴� "����"�� ����
		JMenu  mn_file = new JMenu("����");
		
		//�ָ޴� "�׸��⵵��"�� ����
		JMenu mn_draw = new JMenu("�׸��⵵��");
		
		//�ָ޴� "�׸������"�� ����
		JMenu mn_color = new JMenu("�׸������");
		
		
		//�θ޴� "������"�� ����
		JMenuItem file_new = new JMenuItem("������");
		
		//�θ޴� "����"�� ����
		JMenuItem file_open = new JMenuItem("����");
		
		//�θ޴� "����"�� ����
		JMenuItem file_save = new JMenuItem("����");
		
		//"�θ޴�"���� "�ָ޴�"�� ���
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		
		//�ָ޴� "�׸��⵵��"�� ��� �θ޴�
		JMenuItem draw_line = new JMenuItem("��");
		JMenuItem draw_rect = new JMenuItem("�簢��");
		JMenuItem draw_oval = new JMenuItem("��");
		
		//��,�簢��,�� �θ޴����� "�׸��⵵��"�ָ޴�
		mn_draw.add(draw_line);
		mn_draw.add(draw_rect);
		mn_draw.add(draw_oval);		
		
		
		//�ָ޴� "�׸������"�� ��� �θ޴�
		JMenuItem color_red = new JMenuItem("����");
		JMenuItem color_blue = new JMenuItem("�Ķ�");
		JMenuItem color_green = new JMenuItem("�ʷ�");
		JMenuItem color_other = new JMenuItem("�ٸ�����");
		
		
		//�÷��� ���� �θ޴����� "�׸������"�ָ޴��� ���
		mn_color.add(color_red);
		mn_color.add(color_blue);
		mn_color.add(color_green);
		mn_color.add(color_other);
		
		
		
		//"�ָ޴�"�� "�޴���"�� ��� 
		jmb.add(mn_file);
		
		//"�׸��⵵��" �ָ޴��� "�޴���"�� ���
		jmb.add(mn_draw);
		
		//"�׸��⵵��" �ָ޴��� "�޴���"�� ���
		jmb.add(mn_color);
				
		//"�޴���"�� "������"�� ����
		setJMenuBar(jmb);
		
		//������ JMenuItem�� ���Ͽ� �̺�Ʈ�� ���
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		
		//�׸��⵵���� �θ޴��鿡 ���ؼ��� �̺�Ʈ�� ���
		draw_line.addActionListener(this);
		draw_rect.addActionListener(this);
		draw_oval.addActionListener(this);

		//�׸�������� �θ޴��� ���ؼ��� �̺�Ʈ ���
		color_red.addActionListener(this);
		color_blue.addActionListener(this);
		color_green.addActionListener(this);
		color_other.addActionListener(this);

		
		setSize(600,400);
		setVisible(true);
		setTitle("���׸���");
		}

		
		//������ JMenuItem�� ������ actionPerformed�޼ҵ尡 �����մϴ�.
		@Override
		public void actionPerformed(ActionEvent e) {
			//JMenuItem�߿� � �޴��� ���������� �ľ��ϱ� ���Ͽ� �Ű����� ActionEvent�� getActionCommand()�� �̿�
			String cmd = e.getActionCommand();
			//System.out.println(cmd+"�� ������");
			
			if(cmd.equals("����")) {
				try {
					
					System.out.println("�����մϴ�.");
					//��� � �̸����� �������� �����ϵ��� JFileChooser���̾�α׸� ����.
					int re = jfc.showSaveDialog(this);//Frame �ڽſ��� �ߵ��� this
					
					
					//show���̾�α׿��� ������ ������ 0��, ��Ҹ� ������ 1�� ��ȯ�Ѵ�.
					//������ ������ ������ ����ǰ� �ۼ�
					if(re == 0 ) {	
						//���̾�α׿��� ������ ������ ����´�.
						File file = jfc.getSelectedFile();
						
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(lp.list);
						oos.close();
					}
	
				}catch (Exception ex) {
					System.out.println("���ܹ߻�:"+ex.getMessage());
				}
				
			}else if(cmd.equals("����")) {
				//���⿡ ���� ����� JFileChooser�� �̿��ؼ� ����� ������ �����ϵ��� ����
			
				try {	
				int re =  jfc.showOpenDialog(this); 
				File file = jfc.getSelectedFile();
				
				if(re == 0 ) {	
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					lp.list = (ArrayList<GraphicInfo>)ois.readObject();
					lp.repaint();
					ois.close();		
				}

				}catch (Exception ex) {
					System.out.println("���ܹ߻�:"+ex.getMessage());
				}
				
				
			}else if(cmd.equals("������")) {
				//����ڰ� �׸� �׷����� ����ִ� ����Ʈ�� ��� �����.
				lp.list.clear();
				//�׷����� �׸��� �ǳ��� �ٽ� �׸���.
				lp.repaint();
				
				System.out.println("�������� ó���Դϴ�.");
			}else if(cmd.equals("��")) {
				lp.drawType=0;
			}else if(cmd.equals("�簢��")) {
				lp.drawType=1;
			}else if(cmd.equals("��")) {
				lp.drawType=2;
			}else if(cmd.equals("����")) {
				lp.drawColor = Color.RED;
			}else if(cmd.equals("�Ķ�")) {
				lp.drawColor = Color.BLUE;
			}else if(cmd.equals("�ʷ�")) {
				lp.drawColor = Color.GREEN;
			}else if(cmd.equals("�ٸ�����")) {
				//�ٸ� ������ �����ϵ��� JColorChooser���̾�α׸� ����
				Color color = jcc.showDialog(this, "�׸��� ����", Color.RED);
				if(color != null) {
					lp.drawColor = color;
				}
			
			}
			}
		}
