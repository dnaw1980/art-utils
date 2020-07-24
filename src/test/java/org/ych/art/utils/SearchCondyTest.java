package org.ych.art.utils;

import java.text.ParseException;

import org.junit.Test;

import org.ych.art.utils.bean.SearchCondy;
import org.ych.art.utils.utils.Tools;

/**
 * Unit test for simple App.
 */
public class SearchCondyTest {

	private void show(SearchCondy sc) {
		String fmtStr = "single:%s,\tleft:%s,\tright:%s\n";

		System.out.printf(fmtStr, sc.getSingle(), sc.getLeft(), sc.getRight());

		if (Tools.notEmpty(sc.getDispersed())) {
			System.out.println("离散值开始");
			for (String str : sc.getDispersed()) {
				System.out.println(str);
			}
			System.out.println("离散值结束");
		}
	}

	@Test
	public void parseTest() throws ParseException {

		SearchCondy sc1 = new SearchCondy("10");

		show(sc1);
		
		SearchCondy sc2 = new SearchCondy("10~");

		show(sc2);
		
		SearchCondy sc3 = new SearchCondy("~123.45");

		show(sc3);
		
		SearchCondy sc4 = new SearchCondy("12.69~123.45");

		show(sc4);
		
		SearchCondy sc5 = new SearchCondy("123,asdf,5,89,");

		show(sc5);

	}

}
