package com.analyze.market.domain.requester;

import com.analyze.market.domain.dto.MarketAbstractDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;
import com.analyze.market.domain.util.MarketPaginationUtil;
import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.List;

import static com.analyze.market.domain.requester.MarketAPIConstants.BLOG_SEARCH_API_URL;
import static com.analyze.market.domain.util.MarketHttpParameterUtil.makeGetParameter;

@Service
public class MarketNaverPaginationAPIRequester extends MarketAPIRequester {

    @Override
    public MarketAbstractDTO execute(String query) {
        try {
            if (StringUtils.isEmpty(query)) {
                return new MarketPaginationDTO();
            }
            return template(makeGetParameter(BLOG_SEARCH_API_URL, Lists.newArrayList(new BasicNameValuePair("query", URLEncoder.encode(query, "UTF-8")))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MarketPaginationDTO();
    }

    @Override
    public List<MarketAbstractDTO> execute(String query, MarketPaginationDTO dto, String sortType) {
        return Lists.newArrayList();
    }

    @Override
    public MarketAbstractDTO afterStep(HttpResponse response) {
        MarketPaginationDTO paginationDTO = null;
        try {
            paginationDTO = MarketPaginationUtil.parse(
                    new JSONObject(EntityUtils.toString(response.getEntity()))
            );
        }catch (Exception e) {
            e.printStackTrace();
        }
        return paginationDTO;
    }
}
