package org.ych.art.utils.gen;

import org.ych.art.utils.utils.SessionIDCreator2;

public class RandomTest {

	public static void main(String[] args) {

		for (int i = 0; i != 100; i++) {
			System.out.println(SessionIDCreator2.createUUSessionID());
		}

	}

}
