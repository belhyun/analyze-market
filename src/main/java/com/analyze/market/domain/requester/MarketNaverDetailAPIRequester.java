package com.analyze.market.domain.requester;

import com.analyze.market.domain.dto.MarketAbstractDTO;
import com.analyze.market.domain.dto.MarketDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;
import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.List;

import static com.analyze.market.domain.requester.MarketAPIConstants.BLOG_SEARCH_API_URL;
import static com.analyze.market.domain.util.MarketHttpParameterUtil.makeGetParameter;

public class MarketNaverDetailAPIRequester extends MarketAPIRequester {

    @Override
    public MarketAbstractDTO execute(String query) {
        return new MarketDTO();
    }

    @Override
    public List<MarketAbstractDTO> execute(String query, MarketPaginationDTO paginationDTO, String sortType) {
        List<MarketDTO> result = Lists.newArrayList();
        if (StringUtils.isEmpty(query)) {
            return Lists.newArrayList();
        }
        try {
            for (int i = 1; i <= paginationDTO.getBlogPaginationTotalCount(); i++) {
                MarketDTO marketDTO = (MarketDTO) template(makeGetParameter(BLOG_SEARCH_API_URL, Lists.newArrayList(
                        new BasicNameValuePair("query", URLEncoder.encode(query, "UTF-8")),
                        new BasicNameValuePair("display", Integer.toString(paginationDTO.getBlogPaginationTotalCount())),
                        new BasicNameValuePair("start", Integer.toString(i)),
                        new BasicNameValuePair("sort", sortType))
                ));
                result.add(marketDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    @Override
    public MarketAbstractDTO afterStep(HttpResponse response) {
        return new MarketDTO();
    }
}
