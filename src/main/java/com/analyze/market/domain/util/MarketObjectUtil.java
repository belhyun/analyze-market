package com.analyze.market.domain.util;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MarketObjectUtil {
    public static boolean isNull(Object object) {
        return object == null ? TRUE : FALSE;
    }
}
