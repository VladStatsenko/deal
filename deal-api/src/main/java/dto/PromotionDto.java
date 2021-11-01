package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.statsenko.entity.Promotion;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDto implements Serializable {

    private String name;
    private String desc;
    @JsonProperty("product_id")
    private Integer productId;

    public PromotionDto(Promotion promotion){
        this.name = promotion.getName();
        this.desc = promotion.getDesc();
        this.productId = promotion.getProduct().getId();
    }
}
