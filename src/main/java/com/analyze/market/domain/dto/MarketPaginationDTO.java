package com.analyze.market.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class MarketPaginationDTO {

    /**총건수*/
    private Integer totalCount;

    /**블로그 실제 페이징 수*/
    private Integer blogPaginationTotalCount;

}
