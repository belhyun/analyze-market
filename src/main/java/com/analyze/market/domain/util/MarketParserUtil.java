package com.analyze.market.domain.util;

import com.analyze.market.domain.dto.MarketDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;
import com.google.common.collect.Lists;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class MarketParserUtil {

    private static int PAGE_SIZE = 100;

    public static List<MarketPaginationDTO> parse(JSONObject jsonObject) {
        Integer pageCount = 0, total;
        if (MarketObjectUtil.isNull(jsonObject)) {
            return Lists.newArrayList();
        }
        try {
            total = (int) jsonObject.get("total");
            if (total > 0) {
                pageCount = (int) Math.ceil(total / PAGE_SIZE);
            }
            if (pageCount >= 100) {
                pageCount = 100;
            }
            return Lists.newArrayList(new MarketPaginationDTO(total, pageCount));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    public static List<MarketDTO> parseDetail(JSONObject jsonObject) {
        List<MarketDTO> resultList = Lists.newArrayList();
        if (MarketObjectUtil.isNull(jsonObject)) {
            return Lists.newArrayList();
        }
        try {
            JSONArray items = (JSONArray) jsonObject.get("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                Document title = Jsoup.parse(item.getString("title"));
                Document description = Jsoup.parse(item.getString("description"));
                Document bloggerName = Jsoup.parse(item.getString("description"));
                String link = item.getString("description");
                MarketDTO marketDTO = new MarketDTO(title.text(), description.text(), bloggerName.text(), link);
                resultList.add(marketDTO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
