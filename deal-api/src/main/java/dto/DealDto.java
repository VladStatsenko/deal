package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealDto extends AbstractDto {

    @JsonProperty("deal_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime dealDate;
    private Integer sum;
    private String product;
    private String promotion;

}
