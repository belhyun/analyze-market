package com.analyze.market.domain.util;

import org.apache.http.message.BasicNameValuePair;

public class MarketHttpParameterUtil {

    public static String makeGetParameter(String url, BasicNameValuePair pair) {
        return url + "?" + pair.toString();
    }

}
