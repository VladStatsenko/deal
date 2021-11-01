package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.statsenko.entity.Product;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

    private String name;
    private String desc;
    @JsonProperty("type_id")
    private Integer typeId;
    @JsonProperty("currency_id")
    private Integer currencyId;


    public ProductDto(Product product){
        this.name = product.getName();
        this.desc = product.getDesc();
        this.typeId = product.getProductType().getId();
        this.currencyId = product.getCurrency().getId();
    }
}
