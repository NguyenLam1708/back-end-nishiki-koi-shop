package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderFishDetailForm {
    private long orderFishDetailId;
    private long fishId;
    private int quantity;
    private long price;
    private long orderFishId;
}
