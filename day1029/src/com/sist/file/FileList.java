package com.sist.file;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class FileList extends JFrame {
	JList<String> list;
	
	public FileList() {
		//JList�� ������� ����� �迭 �����
		String [] arr= {"���","����","����","����","���","����","����","����","���","����","����","����"};
		list = new JList<String>(arr);
		
		//JList�� JtextArea�� ���������� �����ӿ� �ٷ� ���� �ʰ�
		//JScrollPane���� ���μ� ��ƾ��Ѵ�.
		//add(list);
		
		JScrollPane jsp_list = new JScrollPane(list);
		add(jsp_list);
		
		
		setSize(400,300);
		setVisible(true);
				
	}
}
