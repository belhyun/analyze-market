package com.analyze.market.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MarketDTO extends MarketAbstractDTO {

    public String title;

    public String description;

    public String bloggerName;

    public String link;

}
