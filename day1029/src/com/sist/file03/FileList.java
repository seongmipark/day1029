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
	
	//JList의 데이터를 위한 벡터를 선언한다.
	Vector vector;
	
	public FileList() {
		
		//벡터를 생성한다.
		vector = new Vector<String>();
		
		//C:\javaStudy\day1025 이 경로에 있는 모든 파일목록으로 JList로 만들기
		
		//디렉토리(파일) 객체로 만든다.
		File dir = new File(path);
		
		//디렉토리 안에 있는 모든 파일이름과, 디렉토리 이름의 목록을 배열로 가져온다.
		String [] arr= dir.list();
		
		
		//디렉토리의 파일목록을 벡터에 담는다.
		for( String str : arr ) {
			vector.add(str);
		}
		
		
		//벡터의 내용으로 JList를 만든다
		list = new JList<Vector<String>>(vector);
		
		//JList도 JtextArea와 마찬가지로 프레임에 바로 담지 않고
		//JScrollPane으로 감싸서 담아야한다.
		//add(list);
		
		//마우스 이벤트 등록
		list.addMouseListener(this);
		
		JScrollPane jsp_list = new JScrollPane(list);
		//add(jsp_list);
		
		
		//삭제버튼 생성
		JButton btn_delete = new JButton("삭제");
		
		//삭제 버튼에 대한 이벤트 등록
		btn_delete.addActionListener(this);
		
		
		jta = new JTextArea();
		JScrollPane jsp_area = new JScrollPane(jta);
		//JList를 담고있는 jsp_list 는 프레임 왼쪽에 담는다.
		add(jsp_list,BorderLayout.WEST);
		//텍스트에리어를 담고 있는 jsp_area는 프레임의 가운데에 담는다.
		add(jsp_area, BorderLayout.CENTER);
		
		//삭제를 위한 버튼을 프레임의 아래쪽에 
		add(btn_delete, BorderLayout.SOUTH);
		
		
		setSize(400,300);
		setVisible(true);
				
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("리스트를 선택했습니다.");
		
		//JList에서 선택한 파일 이름 가져오기
		int idx = list.getSelectedIndex();
		String fileName = (String)vector.get(idx);
		System.out.println(fileName);
		
		try {
			//선택한 파일이름과 파일이 있는 경로를 합쳐서 File객체 혹은 String으로 만들어 
			//파일을 읽기위한 스트림을 생성한다.
			FileReader fr = new FileReader(path+"/"+fileName);
			
			//파일의 내용을 모두 읽어와 누적하기 위한 문자열 변수로 만들고 ""으로 초기화
			String data = "";
			int ch;
			while(true) {
				ch = fr.read();
				if(ch == -1) {
					break;
				}
				data = data + (char)ch;
			}
			
			//현재 JList에서  선택한 파일의 내용을 읽어드려 JTextArea에 담기
			jta.setText(data);
			
			//사용한 파일은 닫기
			fr.close();
			
		} catch (Exception ex) {
			System.out.println("예외발생:"+ex.getMessage());
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
		//삭제하기 위해 삭제할 파일의 File객체로 만든다.
		File file = new File(path+"/"+list.getSelectedValue());		
	
		//파일을 삭제한다.
		file.delete();
			
		//textarea를 깨끗이 비워준다.
		jta.setText("");
		
		//c:/myData폴더의 파일목록을 다시 읽어와서 Vector의 내용을 바꾸고
		//JList를 다시 그리도록 요청한다.
		File dir = new File(path);
		String []arr = dir.list();
		
		//백터를 깨끗이 지우고 다시 읽어온 파일목록을 담아준다.
		vector.clear();
		for(String f:arr) {
			vector.add(f);
		}
		
		//JList의 모양을 바뀐 백터의 내용으로 다시 그려주도록 요청
		list.updateUI();
		
		
		JOptionPane.showMessageDialog(this, "선택한 파일을 삭제했습니다.");
		
	}

}
