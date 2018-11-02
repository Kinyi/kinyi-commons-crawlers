package com.datastory.crawlers.utils;

import com.dataw.crawlers.utils.HtmlUnitUtil;

/**
 * Created by Kinyi_Chan on 30/12/2016.
 */
public class HtmlUnitUtilTest {

    @org.junit.Test
    public void testProcessUrl() throws Exception {

        System.out.println(HtmlUnitUtil.processUrl("http://news.baidu.com/advanced_news.html"));

    }

}