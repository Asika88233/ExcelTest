package com.Asika.ExcelTest;

import java.io.IOException;

import com.Asika.ExcelTest.DataBaseTest.DatabaseController;
import com.Asika.ExcelTest.bean.FileList;

public class Application {
	public static void main(String[] args) {
		Long startTime = System.currentTimeMillis();
		String date = "2019年8月26日";
		FileList fileList = new FileList("测评", "D:\\软文", ".*" + date + ".*");
		fileList.showList();
		ExcelWriter excelWriter = new ExcelWriter();
		try {
			excelWriter.writeExcel(fileList.getList(), "D:\\软文\\te123");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Long endTime = System.currentTimeMillis();
		Long longTime = endTime - startTime;
		System.out.println("生成文件一共使用了" + longTime + "ms");
		startTime = System.currentTimeMillis();
		DatabaseController controller = new DatabaseController();
		controller.addFile(fileList.getList());
		endTime = System.currentTimeMillis();
		longTime = endTime - startTime;
		System.out.println("插入数据库一共使用了" + longTime + "ms");
	}
}
