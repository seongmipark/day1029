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
	
	String fileName = "제목 없음";
	
	JTextArea jta;
	JFileChooser jfc;
	
	
	//생성자에서 JTextArea 객체를 생성해서 JFrame에 담아준다.
	public SistNote() {
		jta = new JTextArea();
		
		jfc = new JFileChooser("c:/myData");
		
		
		//add(jta);
		//텍스트에리어를 바로 프레임을 담으면 화면을 벗어난 글자들이 안보인다.
		//그래서 텍스트에리어는 바로 프래임에 담지 않고
		//스크롤을 자동으로 만들어주는 JScrollPane으로 감싸서 프레임에 담는다.
		
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp);
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu mn_file = new JMenu("파일");
		
		JMenuItem file_new = new JMenuItem("새파일");
		JMenuItem file_open = new JMenuItem("열기");
		JMenuItem file_save = new JMenuItem("저장");
		
		mn_file.add(file_new);
		mn_file.add(file_open);
		mn_file.add(file_save);
		
		jmb.add(mn_file);
		setJMenuBar(jmb);
		
		//메뉴를 눌렀을때 어떤 일을 하도록 이벤트를 등록한다.
		//매개변수 "이벤트처리담당객체"를 전달해야하는데. 이 클래스(SistNote)자신이
		//처리하도록 하기 위해 자기자신을 가리키는 this를 전달
		file_new.addActionListener(this);
		file_open.addActionListener(this);
		file_save.addActionListener(this);
		
		
		//프레임의 크기를 설정하고 보여주도록 설정한다.
		setSize(800,600);
		setVisible(true);
		setTitle(fileName);
		
	
		/*setText기능으로 텍스트를 설정해보기
		jta.setText("안녕하세요");
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
		//메뉴중에 누가 눌러졌는지 파악하기 위해
		//ActionEvent의 getActionCommand 메소드를 이용하여
		//눌러진 메뉴의 글자를 가져온다.
		String cmd = e.getActionCommand();
		
		if(cmd.equals("새파일")) {
			jta.setText("");
			System.out.println("새파일의 처리입니다");
			fileName = "제목 없음";
			setTitle(fileName);
			
			
		}else if(cmd.equals("열기")) {
				
			int re = jfc.showOpenDialog(this);
		
			try {
			if(re == 0) {
				File file = jfc.getSelectedFile();
					FileReader fr = new FileReader(file);
					this.setTitle(file.getName());
					//파일로부터 한글자씩 읽기 위한 변수
					int ch;
					
					//한글자씩 읽어들여 누적하기 위한 문자열 변수
					String data="";
					
					while(true) {
						//파일로부터 한글자씩 읽어오고 해당되는 정수값을 반환한다.
						//더이상 읽어들일 내용이없으면 -1를 반환한다.
						ch=fr.read();
						if(ch == -1) {
							break;
						}
						data = data + (char)ch;
					}
					jta.setText(data);
					
					
					//파일의 사용이 끝나면 닫아준다.
					fr.close();
					JOptionPane.showMessageDialog(this, "파일을 읽어왔습니다.");
					
					//읽어온 파일의 정보를 담고있는 file객체로부터 파일명을 뽑아 fileName에 담는다.
					fileName  = file.getName();
					fileName = fileName.substring(0,fileName.indexOf("."));

					
					setTitle(fileName);
					
				}
			
			}catch (Exception ex) {
					System.out.println("예외발생:"+ex.getMessage());
				}
		}else if(cmd.equals("저장")) {
			//매개변수 this는 이 다이얼로그를 어떤 프레임위에서 띄울것인지 전달
			//현재 이 프레임 SistNote에 띄우라는 의미로 
			//객체 자신을 의미하는 this를 전달
			int re = jfc.showSaveDialog(this);
			//저장버튼을 누르면 0 , 취소를 누르면 1을 반환한다.
			
			if(re == 0) {
				//다이얼로그에서 선택한 파일의 정보를 읽어온다.
				File file = jfc.getSelectedFile();
				
				//선택한 파일에 텍스트에리어에 쓰여진 내용을 출력하기 위한 스트림 생성
				//문자단위의 출력을 위한 Writer의 후소닝 FileWriter를 이용한다.
				//입출력과 관련된 모든 생성자와 메소드들은 예외를 포함하고 있다.
				//그 예외들은 RuntimeException의 후손들이 아니기  때문에 
				//사용자가 반드시 예외처리를 해야 한다.
				
				try {
					FileWriter fw = new FileWriter(file);
					
					//파일로 텍스트에리어의 내용을 출력한다.
					fw.write(jta.getText());
					
					//파일의 사용이 끝나면 닫아준다.
					fw.close();
					
					JOptionPane.showMessageDialog(this, "파일로 저장했습니다.");
					
					//저장한 파일이름으로 제목표시줄을 설정한다.
					fileName  = file.getName();
					//String []arr = fileName.split(".");
					//fileName = arr[0];
					//split메소드는 '.'으로 분리할 수 없었다.
					
					fileName = fileName.substring(0,fileName.indexOf("."));
					
					setTitle(fileName);
					
				} catch (Exception ex) {
					System.out.println("예외발생:"+ex.getMessage());
				}
			}
			
			System.out.println("저장의 처리입니다");
		}
	}

}
