package com.analyze.market.domain.requester;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarketNaverApiRequesterTest {


    @Autowired
    private MarketApiRequester marketNaverApiRequester;

    @Test
    public void test() {
        System.out.println(marketNaverApiRequester.request("가로수길 맛집"));
    }
}