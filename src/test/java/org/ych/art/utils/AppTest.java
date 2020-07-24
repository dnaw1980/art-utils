package org.ych.art.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import org.ych.art.utils.utils.DateUtil;
import org.ych.art.utils.utils.SessionIDCreator;
import org.ych.art.utils.utils.Tools;

/**
 * Unit test for simple App.
 */
public class AppTest {


    @Test
    public void dateTest() throws ParseException {

        Date dt = DateUtil.getDate("2018-04-05");

        System.out.println(dt);
    }

    @Test
    public void SessionIDCreatorTest() throws ParseException {

        final int arrLen = 3;
        String arr[] = new String[arrLen];

        long begin = System.currentTimeMillis();
        // for (int i = 0; i < arrLen; i++) {
        // arr[i] = SessionIDCreator.createUUSessionID();
        // }
        //
        long end = System.currentTimeMillis();
        //
        // System.out.println(end - begin);
        // for (int i = 0; i < arr.length; i++) {
        // System.out.println(arr[i]);
        // }

        // begin = System.currentTimeMillis();
        for (int i = 0; i < arrLen; i++) {
            arr[i] = UUID.randomUUID().toString();
        }

        end = System.currentTimeMillis();

        System.out.println(end - begin);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
