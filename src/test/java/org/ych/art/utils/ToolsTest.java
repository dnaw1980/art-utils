package org.ych.art.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import org.ych.art.utils.utils.DateUtil;
import org.ych.art.utils.utils.Tools;

public class ToolsTest {

    @Test
    public void StrToNumListTest() {

        String src = "|123|345|2.5|.8";

        List<BigDecimal> list = Tools.strToNumList(src, "|");
        for (BigDecimal d : list) {
            System.out.println(d);
        }
    }

    @Test
    public void monthTest() throws ParseException {
        int rs = DateUtil.monthSub(DateUtil.getDate("2018-04-05"), DateUtil.getDate("2019-03-04"));

        System.out.println(rs);
    }

    @Test
    public void getISO8601TimestampTest() throws ParseException {
        String rs = DateUtil.getISO8601Timestamp(DateUtil.getDateTime());

        System.out.println(rs);
    }

    @Test
    public void uuidAddToken() throws ParseException {

        String src = "03000c9bdacc4fe3a8a5c039133887a8";
        System.out.println(src);
        String desc = Tools.uuidAddToken(src);

        System.out.println(desc);
    }

    @Test
    public void uuidRemoveToken() throws ParseException {

        String src = "03000c9b-dacc-4fe3-a8a5-c039133887a8";
        System.out.println(src);
        String desc = Tools.uuidRemoveToken(src);

        System.out.println(desc);
    }

    @Test
    public void is8601Timestamp() throws ParseException {

        String desc = DateUtil.getISO8601Timestamp(DateUtil.getDateTime());

        System.out.println(desc);
    }

    @Test
    public void is8601Timestamp01() throws ParseException {

        java.sql.Timestamp dt = DateUtil.getDateTime();
        System.out.println(dt);
        String desc = DateUtil.getISO8601Timestamp(dt);

        System.out.println(desc);

        desc = DateUtil.dateToString(dt, "yyyy-MM-dd HH:mm:ss");
        System.out.println(desc);
    }

}
