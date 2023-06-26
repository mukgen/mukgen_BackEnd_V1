package com.example.mukgen.infra.feign.gpt;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GptFeignRequest {

    private String model;

    private List<Message> messages;

}

