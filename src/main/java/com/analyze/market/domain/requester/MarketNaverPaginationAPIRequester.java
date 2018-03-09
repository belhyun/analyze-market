package com.analyze.market.domain.requester;

import com.analyze.market.domain.dto.MarketAbstractDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;
import com.analyze.market.domain.util.MarketParserUtil;
import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.List;

import static com.analyze.market.domain.requester.MarketAPIConstants.BLOG_SEARCH_API_URL;
import static com.analyze.market.domain.util.MarketHttpParameterUtil.makeGetParameter;

@Component
public class MarketNaverPaginationAPIRequester extends MarketAPIRequester<MarketPaginationDTO> {

    @Override
    public MarketPaginationDTO execute(String query) {
        try {
            if (StringUtils.isEmpty(query)) {
                return new MarketPaginationDTO();
            }
            return template(makeGetParameter(BLOG_SEARCH_API_URL, Lists.newArrayList(new BasicNameValuePair("query", URLEncoder.encode(query, "UTF-8"))))).get(0);
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
    public List<MarketPaginationDTO> afterStep(HttpResponse response) {
        List<MarketPaginationDTO> paginationDTO = null;
        try {
            paginationDTO = MarketParserUtil.parse(
                    new JSONObject(EntityUtils.toString(response.getEntity()))
            );
        }catch (Exception e) {
            e.printStackTrace();
        }
        return paginationDTO;
    }
}
