package com.analyze.market.domain.util;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class MarketHttpParameterUtil {

    public static String makeGetParameter(String url, List<BasicNameValuePair> pair) {
        return url + "?" + pair.stream().map(BasicNameValuePair::toString).reduce("", (a, b) -> a +  b + "&");
    }
}
