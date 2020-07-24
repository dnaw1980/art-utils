package org.ych.art.utils;

import org.junit.Test;

import org.ych.art.utils.bean.BitMap;
import org.ych.art.utils.utils.Tools;

public class BitMapTest {
	
	@Test
	public void btMapTest() {
		// TODO Auto-generated method stub
		BitMap bm = new BitMap(10);

		boolean b = bm.get(5);
		System.out.println(b);

		bm.set(5, true);

		b = bm.get(5);
		System.out.println(b);

		bm.set(-1, true);
		System.out.println(b);

	}
	
	@Test
	public void isIntTest() {

		System.out.println(Tools.isInteger(""));
		System.out.println(Tools.isInteger("adfds"));
		System.out.println(Tools.isInteger("10r"));
		System.out.println(Tools.isInteger("123.45"));
		System.out.println(Tools.isInteger("453543"));
		System.out.println("------------------------------");
		
		System.out.println(Tools.isDigit(""));
		System.out.println(Tools.isDigit("."));
		System.out.println(Tools.isDigit("adfds"));
		System.out.println(Tools.isDigit("10r"));
		System.out.println(Tools.isDigit("12.3.45"));
		System.out.println(Tools.isDigit("23123"));
		System.out.println(Tools.isDigit(".23123"));
		System.out.println(Tools.isDigit("23432.342"));

	}

}
