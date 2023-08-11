package com.example.mukgen.infra.mail.event.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Setter
@Getter
@ConfigurationProperties("kafka")
@ConstructorBinding
public class KafkaProperty {

    private String serverAddress;

}
