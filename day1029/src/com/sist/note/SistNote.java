package com.sist.note;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class SistNote extends JFrame implements ActionListener {
	
	String fileName = "���� ����";
	
	JTextArea jta;
	JFileChooser jfc;
	
	
	//�����ڿ��� JTextArea ��ü�� �����ؼ� JFrame�� ����ش�.
	public SistNote() {
		jta = new JTextArea();
		
		jfc = new JFileChooser("c:/myData");
		
		
		//add(jta);
		//�ؽ�Ʈ����� �ٷ� �������� ������ ȭ���� ��� ���ڵ��� �Ⱥ��δ�.
		//�׷��� �ؽ�Ʈ������� �ٷ� �����ӿ� ���� �ʰ�
		//��ũ���� �ڵ����� ������ִ� JScrollPane���� ���μ� �����ӿ� ��´�.
		
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp);
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu mn_file = new JMenu("����");
		
		JMenuItem file_new = new JMenuItem("������");
		JMenuItem file_open = new JMenuItem("����");
		JMenuItem file_save = new JMenuItem("����");
		
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		jmb.add(mn_file);
		setJMenuBar(jmb);
		
		//�޴��� �������� � ���� �ϵ��� �̺�Ʈ�� ����Ѵ�.
		//�Ű����� "�̺�Ʈó����簴ü"�� �����ؾ��ϴµ�. �� Ŭ����(SistNote)�ڽ���
		//ó���ϵ��� �ϱ� ���� �ڱ��ڽ��� ����Ű�� this�� ����
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		
		
		//�������� ũ�⸦ �����ϰ� �����ֵ��� �����Ѵ�.
		setSize(800,600);
		setVisible(true);
		setTitle(fileName);
		
	
		/*setText������� �ؽ�Ʈ�� �����غ���
		jta.setText("�ȳ��ϼ���");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		jta.setText("");*/
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�޴��߿� ���� ���������� �ľ��ϱ� ����
		//ActionEvent�� getActionCommand �޼ҵ带 �̿��Ͽ�
		//������ �޴��� ���ڸ� �����´�.
		String cmd = e.getActionCommand();
		
		if(cmd.equals("������")) {
			jta.setText("");
			System.out.println("�������� ó���Դϴ�");
			fileName = "���� ����";
			setTitle(fileName);
			
			
		}else if(cmd.equals("����")) {
				
			int re = jfc.showOpenDialog(this);
		
			try {
			if(re == 0) {
				File file = jfc.getSelectedFile();
					FileReader fr = new FileReader(file);
					this.setTitle(file.getName());
					//���Ϸκ��� �ѱ��ھ� �б� ���� ����
					int ch;
					
					//�ѱ��ھ� �о�鿩 �����ϱ� ���� ���ڿ� ����
					String data="";
					
					while(true) {
						//���Ϸκ��� �ѱ��ھ� �о���� �ش�Ǵ� �������� ��ȯ�Ѵ�.
						//���̻� �о���� �����̾����� -1�� ��ȯ�Ѵ�.
						ch=fr.read();
						if(ch == -1) {
							break;
						}
						data = data + (char)ch;
					}
					jta.setText(data);
					
					
					//������ ����� ������ �ݾ��ش�.
					fr.close();
					JOptionPane.showMessageDialog(this, "������ �о�Խ��ϴ�.");
					
					//�о�� ������ ������ ����ִ� file��ü�κ��� ���ϸ��� �̾� fileName�� ��´�.
					fileName  = file.getName();
					fileName = fileName.substring(0,fileName.indexOf("."));

					
					setTitle(fileName);
					
				}
			
			}catch (Exception ex) {
					System.out.println("���ܹ߻�:"+ex.getMessage());
				}
		}else if(cmd.equals("����")) {
			//�Ű����� this�� �� ���̾�α׸� � ������������ �������� ����
			//���� �� ������ SistNote�� ����� �ǹ̷� 
			//��ü �ڽ��� �ǹ��ϴ� this�� ����
			int re = jfc.showSaveDialog(this);
			//�����ư�� ������ 0 , ��Ҹ� ������ 1�� ��ȯ�Ѵ�.
			
			if(re == 0) {
				//���̾�α׿��� ������ ������ ������ �о�´�.
				File file = jfc.getSelectedFile();
				
				//������ ���Ͽ� �ؽ�Ʈ����� ������ ������ ����ϱ� ���� ��Ʈ�� ����
				//���ڴ����� ����� ���� Writer�� �ļҴ� FileWriter�� �̿��Ѵ�.
				//����°� ���õ� ��� �����ڿ� �޼ҵ���� ���ܸ� �����ϰ� �ִ�.
				//�� ���ܵ��� RuntimeException�� �ļյ��� �ƴϱ�  ������ 
				//����ڰ� �ݵ�� ����ó���� �ؾ� �Ѵ�.
				
				try {
					FileWriter fw = new FileWriter(file);
					
					//���Ϸ� �ؽ�Ʈ�������� ������ ����Ѵ�.
					fw.write(jta.getText());
					
					//������ ����� ������ �ݾ��ش�.
					fw.close();
					
					JOptionPane.showMessageDialog(this, "���Ϸ� �����߽��ϴ�.");
					
					//������ �����̸����� ����ǥ������ �����Ѵ�.
					fileName  = file.getName();
					//String []arr = fileName.split(".");
					//fileName = arr[0];
					//split�޼ҵ�� '.'���� �и��� �� ������.
					
					fileName = fileName.substring(0,fileName.indexOf("."));
					
					setTitle(fileName);
					
				} catch (Exception ex) {
					System.out.println("���ܹ߻�:"+ex.getMessage());
				}
			}
			
			System.out.println("������ ó���Դϴ�");
		}
	}

}
