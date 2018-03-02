package com.analyze.market.domain.util;

import org.apache.http.message.BasicNameValuePair;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MarketHttpParameterUtilTest {

    @Test
    public void test_makeGetParameter() {

        assertEquals(MarketHttpParameterUtil.makeGetParameter("test",
                Lists.newArrayList(
                        new BasicNameValuePair("a","b"),
                        new BasicNameValuePair("c", "d")
                )
        ), "test?a=b&c=d&");
    }

}