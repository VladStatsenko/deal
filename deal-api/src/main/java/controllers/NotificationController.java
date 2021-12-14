package controllers;

import dto.NotificationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/kafka")
public interface NotificationController {

    @PostMapping
    void send(@RequestBody NotificationDto dto);
}
