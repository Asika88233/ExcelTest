package com.Asika.ExcelTest.DataBaseTest;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.Asika.ExcelTest.Util.Parse;
import com.Asika.ExcelTest.bean.TestFile;

public class DatabaseController {
	 private  DataBaseHandler handler;
	 public DataBaseHandler getHandler() {
		return this.handler;
	}
	 public DatabaseController() {
		 handler = new DataBaseHandler();
	 }
	 public void addFile(ArrayList<TestFile> list) {
		 try {
				handler.openConnection();
				for(TestFile i :list) {
					if(handler.queryFileByTitle(i.getTitle())==null) {
						handler.insertFile(i);
						System.out.println("添加成功！标题："+i.getTitle());
					}
					else {
						System.out.println("添加失败！标题："+i.getTitle()+" 已经存在");
					}
				}
				handler.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 public ArrayList <TestFile> getAllFile() throws Exception{
		 handler.openConnection();
		 ResultSet rs =handler.queryAllFile();
		 ArrayList<TestFile>list=Parse.rsToArrayList(rs);
		 handler.closeConnection();
		 return list;
	 }
	 public ArrayList <TestFile> getFileByTitle(String title)throws Exception{
		 handler.openConnection();
		 ResultSet rs =handler.queryFileByTitle(title);
		 ArrayList<TestFile>list=Parse.rsToArrayList(rs);
		 handler.closeConnection();
		 return list;
	}	
	 public ArrayList <TestFile> getFileByDate(Date start,Date end)throws Exception{
		 handler.openConnection();
		 ResultSet rs =handler.queryFileByDate(start,end);
		 ArrayList<TestFile>list=Parse.rsToArrayList(rs);
		 handler.closeConnection();
		 return list;
	 }
}
