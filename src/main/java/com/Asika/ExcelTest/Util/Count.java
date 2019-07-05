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
   	   * @param pattern 匹配汉字
   	   *        pattern1 匹配所有标点符号
   	   *        pattern2 匹配所有单词
   	   */
			int count=0;//每篇文章的字数
	        try {
	            FileInputStream fis = new FileInputStream(file);
	            XWPFDocument xdoc = new XWPFDocument(fis);
	            @SuppressWarnings("resource")
				XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
	            String doc1 = extractor.getText().trim();//去掉空格
	            Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
	            Pattern pattern1 = Pattern.compile("\\pP");             
	            Pattern pattern2 = Pattern.compile("\\w+");
	            Matcher matcher = pattern.matcher(doc1);
	            Matcher matcher1 = pattern1.matcher(doc1);
	            Matcher matcher2 = pattern2.matcher(doc1);
	            while (matcher.find()||matcher1.find()||matcher2.find()) {
	                count++;
	            }
	            fis.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	            return count-2;
	    }
}
