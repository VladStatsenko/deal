package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class DealDto implements Serializable {

    @JsonProperty("deal_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime dealDate;
    private Integer sum;
    private String product;
    private String promotion;

}
