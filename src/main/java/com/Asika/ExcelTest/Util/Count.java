package com.Asika.ExcelTest.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Count {
	 public static int findWordNum( File file) throws IOException {
   	  /**
   	   * @author Asika
   	   * @param pattern 
   	   */
			int count=0;
	        try {
	            FileInputStream fis = new FileInputStream(file);
	            XWPFDocument xdoc = new XWPFDocument(fis);
	            @SuppressWarnings("resource")
				XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
	            String doc1 = extractor.getText().trim();
	            Pattern pattern = Pattern.compile("[\\d][.][\\w][A-z]|[\\u4e00-\\u9fa5]|\\w+|[，【】。《》：!！？]");
	            Matcher matcher = pattern.matcher(doc1);
	            while (matcher.find()) {
	                count++;
	            }
	            fis.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	            return count;
	    }
}
