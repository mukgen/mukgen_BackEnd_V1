package com.example.mukgen.infra.mail.event.configuration;

import com.example.mukgen.domain.mail.controller.dto.request.SendMailRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaConfiguration {

    private final KafkaProperty kafkaProperty;

    @Bean
    public ConsumerFactory<String, SendMailRequest> sendMailRequestConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(
                factoryConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(SendMailRequest.class)
        );
    }

    public Map<String, Object> factoryConfigs(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getServerAddress());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configs;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SendMailRequest> sendMailListener(){
        ConcurrentKafkaListenerContainerFactory<String, SendMailRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(sendMailRequestConsumerFactory());
        return factory;
    }

    @Bean
    public DefaultKafkaProducerFactory<String, SendMailRequest> sendMailRequestDefaultKafkaProducerFactory(){
        return new DefaultKafkaProducerFactory<>(factoryConfigs());
    }

    @Bean
    public KafkaTemplate<String, SendMailRequest> sendMailRequestKafkaTemplate(){
        return new KafkaTemplate<>(sendMailRequestDefaultKafkaProducerFactory());
    }

}
