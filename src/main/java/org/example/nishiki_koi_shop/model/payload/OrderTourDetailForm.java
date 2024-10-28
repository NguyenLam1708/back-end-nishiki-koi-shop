package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderTourDetail;

@Data
@Builder
public class OrderTourDetailForm {

    private Integer numberOfPeople;
    private long price;
    private long tourId;
    private long orderTourId;
}
