package com.analyze.market.domain.requester;

import com.analyze.market.domain.dto.MarketDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;
import com.analyze.market.domain.util.MarketHttpParameterUtil;
import com.analyze.market.domain.util.MarketPaginationUtil;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;

import static com.analyze.market.domain.requester.MarketApiConstants.*;
import static com.analyze.market.domain.requester.MarketApiConstants.EMPTY_STRING;
import static com.analyze.market.domain.util.MarketHttpParameterUtil.*;

@Service
public class MarketNaverApiRequester implements MarketApiRequester {

    @Override
    public MarketPaginationDTO getPaginationCount(String query) {

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            if (StringUtils.isEmpty(query)) {
                return new MarketPaginationDTO();
            }
            HttpGet request = new HttpGet(makeGetParameter(BLOG_SEARCH_API_URL, new BasicNameValuePair("query", URLEncoder.encode(query, "UTF-8"))));
            request.addHeader("X-Naver-Client-Id", CLIENT_ID);
            request.addHeader("X-Naver-Client-Secret", CLIENT_SECRET);
            HttpResponse httpResponse = httpClient.execute(request);
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode == HttpStatus.OK.value()) {
                MarketPaginationDTO paginationDTO = MarketPaginationUtil.parse(
                        new JSONObject(EntityUtils.toString(httpResponse.getEntity()))
                );
                return paginationDTO;
            }
            return new MarketPaginationDTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MarketPaginationDTO();
    }

    @Override
    public MarketDTO getPost(String query, MarketPaginationDTO dto) {
        return null;
    }
}
