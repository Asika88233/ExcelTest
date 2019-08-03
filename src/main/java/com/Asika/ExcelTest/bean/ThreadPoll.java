package com.Asika.ExcelTest.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import com.Asika.ExcelTest.Util.GetRange;

public class ThreadPoll {
	  private Map<Integer,File> map;
	  private Integer threadNums;
	  
	  public  ThreadPoll(Map<Integer,File> map,Integer threadNums) {
		    this.map=map;
		    this.threadNums=threadNums;
	}
	  public  Thread[] initThreads(ArrayList<TestFile> list,Integer size) {
		  Thread a[]=new Thread[this.threadNums];
		  for (int i = 0; i <this.threadNums; i++) {
			  ReadThread readThread = new ReadThread(map, GetRange.getRange(size, this.threadNums).get(i),list);
			  Thread t=new Thread(readThread);
			  a[i]=t;
		}
		  return a;
	}
	  public void readFileNums (Thread[]threads) {
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
