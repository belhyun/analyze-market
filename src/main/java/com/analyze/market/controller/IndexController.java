package com.analyze.market.controller;

import com.analyze.market.domain.dto.MarketDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;
import com.analyze.market.domain.requester.MarketAPIRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class IndexController {

    @Resource
    private MarketAPIRequester<MarketPaginationDTO> marketNaverPaginationAPIRequester;

    @Resource
    private MarketAPIRequester<MarketDTO> marketNaverDetailAPIRequester;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }

    @RequestMapping("/search")
    public ModelAndView search(Map<String, Object> model, @RequestParam(value = "query") String query) {
        ModelAndView mnv = new ModelAndView("search");
        MarketPaginationDTO paginationDTO = marketNaverPaginationAPIRequester.execute(query);
        mnv.addObject("details", marketNaverDetailAPIRequester.execute(query, paginationDTO, "sim"));
        mnv.addObject("query", query);
        return mnv;
    }
}
