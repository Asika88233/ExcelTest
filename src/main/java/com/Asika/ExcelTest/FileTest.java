package com.Asika.ExcelTest;

import com.Asika.DataBaseTest.DatabaseController;

public class FileTest {
     public static void main(String[] args) { 
		  FileList fileList =new FileList("测评","D:\\万万",".*"+""+".*"); 
		   DatabaseController controller = new DatabaseController();
		   controller.addFile(fileList.getList());
		   ExcelWriter writer = new ExcelWriter();
		   writer.writeExcel(fileList.getList(), "C:\\Users\\Administrator\\Desktop\\4.xlsx");
	}
}

