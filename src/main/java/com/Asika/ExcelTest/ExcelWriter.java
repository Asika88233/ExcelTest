package com.Asika.ExcelTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	public void writeExcel(ArrayList<TestFile> list, String path) throws IOException {
		long startTime=System.currentTimeMillis();
		File f = new File("D:\\workspace\\ExcelTest\\src\\main\\java\\temple.xlsx");
		FileInputStream in =new FileInputStream(f);
		//读取excel模板
		XSSFWorkbook wb = new XSSFWorkbook(in);
		//读取了模板内所有sheet内容
		XSSFSheet sheet = wb.getSheetAt(0);
		//如果这行没有了，整个公式都不会有自动计算的效果的
		sheet.setForceFormulaRecalculation(true);
		int j = 2;
		for (TestFile i : list) {
			sheet.getRow(j).getCell(2).setCellValue(i.getTitle());
			sheet.getRow(j).getCell(3).setCellValue(i.getNum());
			sheet.getRow(j).getCell(4).setCellValue(i.getSalary());
			sheet.getRow(j).getCell(5).setCellValue(i.getTime());
			j++;
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
			wb.write(stream);// 将excel文件流写入
			stream.close();
			long endTime=System.currentTimeMillis();
			long createTime=endTime-startTime;
			System.out.println("生成文件所需时间"+createTime+"ms");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
