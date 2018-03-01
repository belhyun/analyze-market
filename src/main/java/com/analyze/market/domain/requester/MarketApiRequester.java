package com.analyze.market.domain.requester;

import com.analyze.market.domain.dto.MarketDTO;
import com.analyze.market.domain.dto.MarketPaginationDTO;

public interface MarketApiRequester {
    MarketPaginationDTO getPaginationCount(String query);
    MarketDTO getPost(String query, MarketPaginationDTO dto);
}
