package com.example.mukgen.infra.fcm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("flarelane")
public class FlareLaneProperty {

    private String segmentsId;

    private String apiSecretKey;

}
