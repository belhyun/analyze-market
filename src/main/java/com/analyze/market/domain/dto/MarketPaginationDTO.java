package com.analyze.market.domain.dto;

import lombok.Data;

@Data
public class MarketPaginationDTO {

    /**총건수*/
    private Integer totalCount;

    /**블로그 실제 페이징 수*/
    private Integer blogPaginationTotalCount;

    /**블로그 처리할 수 있는 페이징 수*/
    private Integer blogPaginationCount;
}
