package com.Asika.ExcelTest;

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

public class readThread  implements Runnable{
	      private HashMap<Integer,File> map;
          private Range range;
          private AtomicIntegerArray num;
          @Override
		public   void  run() {
			for(int i=this.range.getStart();i<=this.range.getEnd();i++) {
				File file=map.get(i);
				try {
					num.set(i, Count.findWordNum(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
          public readThread(Map<Integer,File> map,Range range, AtomicIntegerArray num) {
        	  this.map=(HashMap<Integer, File>) map;
        	  this.num=num;
        	  this.range=range;
          }
}
