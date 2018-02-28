package com.analyze.market.domain.requester;

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
    public String request(String query) {

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            if (StringUtils.isEmpty(query)) {
                return EMPTY_STRING;
            }
            HttpGet request = new HttpGet(makeGetParameter(BLOG_SEARCH_API_URL, new BasicNameValuePair("query", URLEncoder.encode(query, "UTF-8"))));
            request.addHeader("X-Naver-Client-Id", CLIENT_ID);
            request.addHeader("X-Naver-Client-Secret", CLIENT_SECRET);
            HttpResponse httpResponse = httpClient.execute(request);
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode == HttpStatus.OK.value()) {
                JSONObject jsonObject = new JSONObject(httpResponse.getEntity().getContent().toString());
                MarketPaginationDTO paginationDTO = MarketPaginationUtil.parse(jsonObject);
                return EntityUtils.toString(httpResponse.getEntity());
            }
            return EMPTY_STRING;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EMPTY_STRING;
    }
}
