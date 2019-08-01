package com.Asika.ExcelTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerArray;

import com.Asika.ExcelTest.Util.GetRange;

public class ThreadPoll {
	  private Map<Integer,File> map;
	  private Integer threadNums;
	  
	  public  ThreadPoll(Map<Integer,File> map,Integer threadNums) {
		    this.map=map;
		    this.threadNums=threadNums;
	}
	  public  Thread[] initThreads(AtomicIntegerArray  num,Integer size) {
		  Thread a[]=new Thread[this.threadNums];
		  for (int i = 0; i <this.threadNums; i++) {
			  readThread readThread = new readThread(map, GetRange.getRange(size, this.threadNums).get(i), num);
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
