package com.Asika.ExcelTest.bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicIntegerArray;

import com.Asika.ExcelTest.Util.Count;

public class ReadThread  implements Runnable{
	      private HashMap<Integer,File> map;
          private Range range;
          private ArrayList<TestFile> list;
          @Override
		public   void  run() {
			for(int i=this.range.getStart();i<=this.range.getEnd();i++) {
				File file=map.get(i);
				try {
					list.get(i-1).setNum(Count.findWordNum(file));;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
          public ReadThread(Map<Integer,File> map,Range range, ArrayList<TestFile> list) {
        	  this.map=(HashMap<Integer, File>) map;
        	  this.list=list;
        	  this.range=range;
          }
}
