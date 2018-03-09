package com.analyze.market.domain.requester;

import com.analyze.market.domain.dto.MarketDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarketAPIRequesterIntegrationTest {

    @Resource
    private MarketAPIRequester<MarketPaginationDTO> marketNaverPaginationAPIRequester;

    @Resource
    private MarketAPIRequester<MarketDTO> marketNaverDetailAPIRequester;

    @Test
    public void test() {
        MarketPaginationDTO 가로수길_맛집 = marketNaverPaginationAPIRequester.execute("가로수길 맛집");
        marketNaverDetailAPIRequester.execute("가로수길 맛집", 가로수길_맛집, "sim");
    }
}