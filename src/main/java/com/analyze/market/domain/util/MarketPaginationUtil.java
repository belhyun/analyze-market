package com.analyze.market.domain.util;

import com.analyze.market.domain.dto.MarketPaginationDTO;
import org.codehaus.jettison.json.JSONObject;

public class MarketPaginationUtil {

    public static MarketPaginationDTO parse(JSONObject jsonObject) {
        return new MarketPaginationDTO();
    }
}
