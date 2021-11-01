package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.statsenko.entity.Deal;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealDto implements Serializable {

    @JsonProperty("deal_date")
    private Date dealDate;
    private Integer sum;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("promotion_id")
    private Integer promotionId;

    public DealDto(Deal deal){
        this.dealDate = deal.getDealDate();
        this.sum = deal.getSum();
        this.productId = deal.getProduct().getId();
        this.promotionId = deal.getPromotion().getId();
    }
}
