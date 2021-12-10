package org.statsenko;

import com.fasterxml.jackson.databind.DeserializationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.JsonParser;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.IOException;

@EnableEurekaClient
@SpringBootApplication
public class DealApplication {
    public static void main(String[] args) {
        SpringApplication.run(DealApplication.class,args);
    }

    @Bean
    public JsonDeserializer jsonDeserializer() {
        return new JsonDeserializer() {
            public Object deserialize(JsonParser p, DeserializationContext context) throws IOException {
                return null;
            }
        };
    }
}
