package com.analyze.market.domain.util;

import com.analyze.market.domain.dto.MarketPaginationDTO;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class MarketPaginationUtil {

    private static int PAGE_SIZE = 100;

    public static MarketPaginationDTO parse(JSONObject jsonObject) {
        Integer pageCount = 0, total;
        if (MarketObjectUtil.isNull(jsonObject)) {
            return new MarketPaginationDTO();
        }
        try {
            total = (int) jsonObject.get("total");
            if (total > 0) {
                pageCount = (int) Math.ceil(total / PAGE_SIZE);
            }
            return new MarketPaginationDTO(total, pageCount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new MarketPaginationDTO();
    }
}
