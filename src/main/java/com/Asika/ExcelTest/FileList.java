package com.Asika.ExcelTest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Asika.ExcelTest.Util.Count;

public class FileList {
	  private ArrayList<TestFile>list;
     
      public FileList(String pattern,String path,String date) {
    	  File f = new File(path);
	        if (!f.exists()) {
	            System.out.println(path + " not exists");
	        }
	        else {
	    		 ArrayList <TestFile>List = new ArrayList<TestFile>();
	    		 File[]fileName=f.listFiles();
	    		    String patternString =".*"+pattern+".*"+".docx";
	    		    Pattern r = Pattern.compile(patternString);
	    		    Pattern date1 = Pattern.compile(date);
	    		 for (File i : fileName) { 
	    			 Calendar cal = Calendar.getInstance();
	             	 long time = i.lastModified();
	             	  cal.setTimeInMillis(time);
	    				Matcher m = r.matcher(i.getName());
	    				@SuppressWarnings("deprecation")
						Matcher d  = date1.matcher(cal.getTime().toLocaleString());
	    				if(m.matches()&&d.matches()) {
	    					 if (i.isDirectory()) {
	    			                System.out.println(i.getName() + " [文件夹]");
	    			            } else {
	    			            	TestFile testFile = new TestFile();
	    			    			 if (pattern.equals("测评")) {
	    								testFile.setSalary(80);
	    								}
	    			    			 else {
										testFile.setSalary(50);
									}
	    			            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
	    			            	String sd = sdf.format(cal.getTime());
	    			            	testFile.setTime(sd);
	    			            	testFile.setTitle(i.getName().replaceAll(".docx",""));
	    			            	try {
	    			            		testFile.setNum(Count.findWordNum(i));
	    								
	    							} catch (IOException e) {
	    								// TODO Auto-generated catch block
	    								e.printStackTrace();
	    							}
	    			            	if (testFile.getNum()>0) {
	    			            		List.add(testFile);
	    							}
	    			            }
	    				}
	    		 }
	    		Collections.sort(List, new Comparator<TestFile>() {
	    	            @Override
	    	            public int compare(TestFile o1, TestFile o2) {
	    	                if (o1.getTime().compareTo(o2.getTime())<1) {
	    	                	return -1;
	    	                }
	    					else {
	    						return 1;
	    					}
	    	            }
	    	        });
	    		 this.list=List;
	        }
	}
      public FileList() {
		// TODO Auto-generated constructor stub
	}
      public ArrayList<TestFile> getList() {
	return list;
}
      public void setList(ArrayList<TestFile> list) {
	this.list = list;
}
      public void showList() {
	for(TestFile i:this.getList()) {
		System.out.println(i.getTitle());
	}
}
}
