package com.test.shop.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemInventoryResponse {
    private Integer id;
    private String item;
    private Integer currentQuality;
    private Integer daysRemainingToSell;
}
