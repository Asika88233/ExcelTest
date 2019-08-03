package com.Asika.ExcelTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Asika.ExcelTest.bean.TestFile;

public class ExcelWriter {
	
	private XSSFWorkbook workBook;

	public void writeExcel(ArrayList<TestFile> list, String path) {
		long startTime=System.currentTimeMillis();
		String[] title = { "名称", "字数", "薪酬", "时间" };// 定义表头
		workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet();// 创建工作表sheet
		XSSFRow row = sheet.createRow(0);// 创建第一行
		XSSFCell cell = null;
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		} // 插入第一行数据的表头
		int j = 1;
		for (TestFile i : list) {
			j++;
			XSSFRow nrow = sheet.createRow(j);
			XSSFCell ncell = nrow.createCell(0);
			ncell.setCellValue("" + i.getTitle());
			ncell = nrow.createCell(1);
			ncell.setCellValue(i.getNum());
			ncell = nrow.createCell(2);
			ncell.setCellValue(i.getSalary());
			ncell = nrow.createCell(3);
			ncell.setCellValue(i.getTime());
		}
		j = j - 1;
		System.out.println("一共生成了" + j + "条信息");
		File file = new File(path+".xlsx");
   	for (int k= 1; file.exists(); k++) {
			if(k==1) {
				StringBuffer sb = new StringBuffer();
				sb.append(path);
				path += "(" + k + ")";
				System.out.println("文件已经存在,将重新为您生成,新文件路径为：" + path);
				file = new File(path+".xlsx");
			}
			else {
				int i=k-1;
				path=path.replaceAll("\\(" + i+ "\\)",  "\\(" + k + "\\)");
				System.out.println("文件已经存在,将重新为您生成,新文件路径为：" + path);
				file = new File(path+".xlsx");
			}
		}
		try {
			file.createNewFile();// 创建文件对象
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workBook.write(stream);// 将excel文件流写入
			stream.close();
			long endTime=System.currentTimeMillis();
			long createTime=endTime-startTime;
			System.out.println("生成文件所需时间"+createTime+"ms");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
