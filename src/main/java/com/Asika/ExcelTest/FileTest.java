package com.Asika.ExcelTest;


public class FileTest {
     public static void main(String[] args) {
    	 Long startTime=System.currentTimeMillis();
		  String date = ""; FileList fileList =new
		  FileList("测评","D:\\软文",".*"+date+".*"); fileList.showList(); ExcelWriter
		  excelWriter = new ExcelWriter(); excelWriter.writeExcel(fileList.getList(), "D:\\软文\\te123");
			Long endTime=System.currentTimeMillis();
			Long longTime=endTime-startTime;
			System.out.println("一共使用了"+longTime+"ms");
	}
}

