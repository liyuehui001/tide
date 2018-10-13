package com.predict.tide.black.tide.tideCode.utils;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by black on 2018/4/21.
 */
public class MUtilsTest {
    @Test
    public void getDateList() throws Exception {
        ArrayList<String> datelist = MUtils.getDateList(15);
        for (int i = 0; i < datelist.size(); i++) {
//            Log.e("riqi",datelist.get(i));
//            assertEquals(datelist.get(i),"2018-05-01");
            System.out.println(datelist.get(i));
        }
    }


}