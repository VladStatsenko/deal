package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class NotificationDto implements Serializable {

    private String name;
    private String status;
}
