package com.Asika.ExcelTest.Util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class CountTest {

	@Test
	public void testFindWordNum() throws IOException {
		File file =new File("D:\\软文\\【测评】-Asika-《剑与轮回》——操控时间，冒险异界.docx");
		int testNum=Count.findWordNum(file);
		int num=2031;
		assertEquals(num, testNum);
	}

}
