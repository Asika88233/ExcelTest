package com.Asika.ExcelTest.Util;

import java.util.HashMap;
import java.util.Map;

import com.Asika.ExcelTest.bean.Range;

public class GetRange {
	public static  Map<Integer, Range> getRange(int num,int threads) {
		Map<Integer, Range>threadRange=new HashMap<Integer, Range>();
		int base=num/threads;
		int extra=num%threads;
		int remove=0;
		for(int i=0;i<threads;i++) {
			int start=base*(i)+1+remove;
			int end=base*(i+1)+remove;
			if (extra>0) {
				end++;
				extra--;
				remove++;
			}
			Range range=new Range();
			range.setStart(start);
			range.setEnd(end);
			threadRange.put(i, range);
		}
		return threadRange;
	}
}
