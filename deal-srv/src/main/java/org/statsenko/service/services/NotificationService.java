package org.statsenko.service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("org.statsenko.config")
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final KafkaTemplate<Long, NotificationDto> kafkaTemplate;

    @Value(value = "${kafka.topic.to_bank}")
    private String greetingTopicName;

    public void send(NotificationDto dto) {
        log.info("<= sending {}", dto);
        kafkaTemplate.send(greetingTopicName, dto);
    }

    @KafkaListener(topics = "${kafka.topic.to_analytic}", groupId = "${kafka.consumer.id}",containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload NotificationDto dto) {
        log.info("=> consumed {}", dto);
    }
}
