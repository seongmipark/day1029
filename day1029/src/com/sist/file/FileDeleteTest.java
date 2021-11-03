package com.sist.file;

import java.io.File;


public class FileDeleteTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//*C/myData /'
		File file = new File("C:/myData/hello.txt");
		
		file.delete();
		System.out.println("삭제했습니다.");;
		
	}

}
