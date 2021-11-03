package com.sist.file03;

import java.io.File;
import java.io.FileReader;
import java.util.Vector;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class FileList extends JFrame implements MouseListener, ActionListener {
	JList<Vector<String>> list;
	JTextArea jta;
	String path = "c:/myData";
	
	//JList�� �����͸� ���� ���͸� �����Ѵ�.
	Vector vector;
	
	public FileList() {
		
		//���͸� �����Ѵ�.
		vector = new Vector<String>();
		
		//C:\javaStudy\day1025 �� ��ο� �ִ� ��� ���ϸ������ JList�� �����
		
		//���丮(����) ��ü�� �����.
		File dir = new File(path);
		
		//���丮 �ȿ� �ִ� ��� �����̸���, ���丮 �̸��� ����� �迭�� �����´�.
		String [] arr= dir.list();
		
		
		//���丮�� ���ϸ���� ���Ϳ� ��´�.
		for( String str : arr ) {
			vector.add(str);
		}
		
		
		//������ �������� JList�� �����
		list = new JList<Vector<String>>(vector);
		
		//JList�� JtextArea�� ���������� �����ӿ� �ٷ� ���� �ʰ�
		//JScrollPane���� ���μ� ��ƾ��Ѵ�.
		//add(list);
		
		//���콺 �̺�Ʈ ���
		list.addMouseListener(this);
		
		JScrollPane jsp_list = new JScrollPane(list);
		//add(jsp_list);
		
		
		//������ư ����
		JButton btn_delete = new JButton("����");
		
		//���� ��ư�� ���� �̺�Ʈ ���
		btn_delete.addActionListener(this);
		
		
		jta = new JTextArea();
		JScrollPane jsp_area = new JScrollPane(jta);
		//JList�� ����ִ� jsp_list �� ������ ���ʿ� ��´�.
		add(jsp_list,BorderLayout.WEST);
		//�ؽ�Ʈ����� ��� �ִ� jsp_area�� �������� ����� ��´�.
		add(jsp_area, BorderLayout.CENTER);
		
		//������ ���� ��ư�� �������� �Ʒ��ʿ� 
		add(btn_delete, BorderLayout.SOUTH);
		
		
		setSize(400,300);
		setVisible(true);
				
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("����Ʈ�� �����߽��ϴ�.");
		
		//JList���� ������ ���� �̸� ��������
		int idx = list.getSelectedIndex();
		String fileName = (String)vector.get(idx);
		System.out.println(fileName);
		
		try {
			//������ �����̸��� ������ �ִ� ��θ� ���ļ� File��ü Ȥ�� String���� ����� 
			//������ �б����� ��Ʈ���� �����Ѵ�.
			FileReader fr = new FileReader(path+"/"+fileName);
			
			//������ ������ ��� �о�� �����ϱ� ���� ���ڿ� ������ ����� ""���� �ʱ�ȭ
			String data = "";
			int ch;
			while(true) {
				ch = fr.read();
				if(ch == -1) {
					break;
				}
				data = data + (char)ch;
			}
			
			//���� JList����  ������ ������ ������ �о��� JTextArea�� ���
			jta.setText(data);
			
			//����� ������ �ݱ�
			fr.close();
			
		} catch (Exception ex) {
			System.out.println("���ܹ߻�:"+ex.getMessage());
		}
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//�����ϱ� ���� ������ ������ File��ü�� �����.
		File file = new File(path+"/"+list.getSelectedValue());		
	
		//������ �����Ѵ�.
		file.delete();
			
		//textarea�� ������ ����ش�.
		jta.setText("");
		
		//c:/myData������ ���ϸ���� �ٽ� �о�ͼ� Vector�� ������ �ٲٰ�
		//JList�� �ٽ� �׸����� ��û�Ѵ�.
		File dir = new File(path);
		String []arr = dir.list();
		
		//���͸� ������ ����� �ٽ� �о�� ���ϸ���� ����ش�.
		vector.clear();
		for(String f:arr) {
			vector.add(f);
		}
		
		//JList�� ����� �ٲ� ������ �������� �ٽ� �׷��ֵ��� ��û
		list.updateUI();
		
		
		JOptionPane.showMessageDialog(this, "������ ������ �����߽��ϴ�.");
		
	}

}
