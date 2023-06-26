package com.example.mukgen.infra.feign.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GptResponse {

    List<choice> choices;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class choice{
        private Message message;
    }
}
