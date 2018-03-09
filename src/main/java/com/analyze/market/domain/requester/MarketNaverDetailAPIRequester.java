package com.analyze.market.domain.requester;

import com.analyze.market.domain.dto.MarketDTO;
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

import static com.analyze.market.domain.requester.MarketAPIConstants.BLOG_DETAIL_SEARCH_API_URL;
import static com.analyze.market.domain.util.MarketHttpParameterUtil.makeGetParameter;

@Component
public class MarketNaverDetailAPIRequester extends MarketAPIRequester<MarketDTO> {

    @Override
    public MarketDTO execute(String query) {
        return new MarketDTO();
    }

    @Override
    public List<MarketDTO> execute(String query, MarketPaginationDTO paginationDTO, String sortType) {
        List<MarketDTO> result = Lists.newArrayList();
        if (StringUtils.isEmpty(query)) {
            return Lists.newArrayList();
        }
        try {
            for (int i = 1; i <= paginationDTO.getBlogPaginationTotalCount(); i++) {
                List<MarketDTO> marketDTO = template(makeGetParameter(BLOG_DETAIL_SEARCH_API_URL, Lists.newArrayList(
                        new BasicNameValuePair("query", URLEncoder.encode(query, "UTF-8")),
                        new BasicNameValuePair("display", Integer.toString(paginationDTO.getBlogPaginationTotalCount())),
                        new BasicNameValuePair("start", Integer.toString(i)),
                        new BasicNameValuePair("sort", sortType))
                ));
                result.addAll(marketDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<MarketDTO> afterStep(HttpResponse response) {
        List<MarketDTO> resultList = Lists.newArrayList();
        try {
            resultList = MarketParserUtil.parseDetail(
                    new JSONObject(EntityUtils.toString(response.getEntity()))
            );
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
