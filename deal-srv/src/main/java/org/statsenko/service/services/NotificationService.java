package org.statsenko.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final KafkaTemplate<Long, NotificationDto> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Value(value = "${kafka.topic.to_analytic}")
    private String greetingTopicName;

    @Scheduled(initialDelay = 10000, fixedDelay = 5000)
    public void produce() {
        NotificationDto dto = new NotificationDto("Client","NEW");
        log.info("<= sending {}", writeValueAsString(dto));
        kafkaStarshipTemplate.send(greetingTopicName, dto);
    }

    private String writeValueAsString(NotificationDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
