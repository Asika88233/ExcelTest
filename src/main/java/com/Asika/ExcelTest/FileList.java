package com.Asika.ExcelTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.record.chart.TickRecord;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;

import com.Asika.ExcelTest.Util.GetRange;

public class FileList {
	private ArrayList<TestFile> list;
	public FileList(String pattern, String path, String date) {
		Map<Integer, File> map = new HashMap<Integer, File>();
		ArrayList<TestFile> List = new ArrayList<TestFile>();
		int pivro = 1;
		File f = new File(path);
		if (!f.exists()) {
			System.out.println(path + " not exists");
		} else {
			File[] fileName = f.listFiles();
			String patternString = ".*" + pattern + ".*" + ".docx";
			Pattern r = Pattern.compile(patternString);
			Pattern date1 = Pattern.compile(date);
			for (File i : fileName) {
				Calendar cal = Calendar.getInstance();
				long time = i.lastModified();
				cal.setTimeInMillis(time);
				Matcher m = r.matcher(i.getName());
				@SuppressWarnings("deprecation")
				Matcher d = date1.matcher(cal.getTime().toLocaleString());
				if (m.matches() && d.matches()&&!i.getName().equals("~$戏测评】-Asika-《创世纪元》——冒险异世，激情国战.docx")) {
					if (i.isDirectory()) {
						System.out.println(i.getName() + " [文件夹]");
					} else {
						map.put(pivro, i);
						pivro++;
						TestFile testFile = new TestFile();
						if (pattern.equals("测评")) {
							testFile.setSalary(80);
						} else {
							testFile.setSalary(50);
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
						String sd = sdf.format(cal.getTime());
						testFile.setTime(sd);
						testFile.setTitle(i.getName().replaceAll(".docx", ""));
							List.add(testFile);
					}
				}
			}
			AtomicIntegerArray  num=new AtomicIntegerArray(List.size()+1);
			readThread readThread1 = new readThread(map, GetRange.getRange(List.size(), 4).get(0), num);
			readThread readThread2 = new readThread(map, GetRange.getRange(List.size(), 4).get(1), num);
			readThread readThread3 = new readThread(map, GetRange.getRange(List.size(), 4).get(2), num);
			readThread readThread4 = new readThread(map, GetRange.getRange(List.size(), 4).get(3), num);
			Thread t1=new Thread(readThread1);
		    Thread t2=new Thread(readThread2);
		    Thread t3=new Thread(readThread3);
		    Thread t4=new Thread(readThread4);
		    t1.start();
		    t2.start();
		    t3.start();
		    t4.start();
		    int j=1;
		    try {
				t4.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    	 for (TestFile i:List) {
						i.setNum(num.get(j));
						j++;
					}
			Collections.sort(List, new Comparator<TestFile>() {
				@Override
				public int compare(TestFile o1, TestFile o2) {
					if (o1.getTime().compareTo(o2.getTime()) < 1) {
						return -1;
					} else {
						return 1;
					}
				}
			});
			this.list = List;
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
		for (TestFile i : this.getList()) {
			System.out.println(i.getTitle());
		}
	}
}