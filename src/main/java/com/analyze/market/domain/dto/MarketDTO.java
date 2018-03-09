package com.analyze.market.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarketDTO extends MarketAbstractDTO {

    private String title;

    private String description;

    private String bloggerName;

    private String link;

}
