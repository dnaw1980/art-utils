package org.ych.art.utils.http;

import org.junit.Test;

import org.ych.art.utils.utils.http.HttpResponseException;
import org.ych.art.utils.utils.http.HttpSendUtils;

public class HttpSenderTest {
	
	@Test
	public void httpGetTest() {
		String rsp = null;
		try {
			rsp = HttpSendUtils.getRequest("http://192.168.1.215/superx-eureka-");
		} catch (HttpResponseException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getBody());
		}
		System.out.println(rsp);
	}

}
