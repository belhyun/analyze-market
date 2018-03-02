package com.analyze.market.domain.requester;

import com.analyze.market.domain.dto.MarketAbstractDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;
import com.analyze.market.domain.exception.MarketNotFoundException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

import static com.analyze.market.domain.requester.MarketAPIConstants.CLIENT_ID;
import static com.analyze.market.domain.requester.MarketAPIConstants.CLIENT_SECRET;

public abstract class MarketAPIRequester {

    public abstract MarketAbstractDTO execute(String query);

    public abstract List<MarketAbstractDTO> execute(String query, MarketPaginationDTO dto, String sortType);

    public MarketAbstractDTO template(String url) throws IOException, MarketNotFoundException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse;
        HttpGet request = new HttpGet(url);
        request.addHeader("X-Naver-Client-Id", CLIENT_ID);
        request.addHeader("X-Naver-Client-Secret", CLIENT_SECRET);
        try {
            httpResponse = httpClient.execute(request);
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode == HttpStatus.OK.value()) {
                return afterStep(httpResponse);
            }
        } catch (IOException e) {
            throw e;
        }
        throw new MarketNotFoundException();
    }

    public abstract MarketAbstractDTO afterStep(HttpResponse response);
}
