package com.Asika.ExcelTest.Util;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.Asika.ExcelTest.bean.TestFile;

public class Parse {
	/**
	 * 
	 * @param rs
	 * @return list 将数据库查询的ResultSet转换为包装了TestFile类的ArrayList对象
	 * @throws Exception
	 */
       public static ArrayList<TestFile> rsToArrayList(ResultSet rs) throws Exception {
    	   ArrayList<TestFile>list =  new ArrayList<TestFile>();
    	   while (rs.next()) {
    		TestFile i = new TestFile();
			i.setNum(rs.getInt("num"));
			i.setTitle(rs.getString("title"));
			i.setTime(rs.getDate("date").toString());
			list.add(i);
		}
		return list;
	}
       /**
        * 
        * @param dateString
        * @return sqlDate 将String类型的字符串转换为java.sql.Date类型，支持yyyy-MM-dd或者yyyy/MM/dd格式的字符串;
        * @throws ParseException
        */
       public static Date stringToDate(String dateString) throws ParseException {
    	   SimpleDateFormat bartDateFormat =  new SimpleDateFormat("yyyy-MM-dd");  
		   String dateToParse = dateString.replaceAll("/", "-");
		   java.util.Date date = bartDateFormat.parse(dateToParse);  
	       java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	       return sqlDate;
	}
}
