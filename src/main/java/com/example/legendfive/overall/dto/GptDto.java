package com.example.legendfive.overall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class GptDto {
    @Data
    @NoArgsConstructor
    public static class GptRequestDto implements Serializable {
        private String model;
        @JsonProperty("max_tokens")
        private Integer maxTokens;
        private Double temperature;
        private List<GptMessage> messages;

        @Builder
        public GptRequestDto(String question) {
            this.model = "gpt-3.5-turbo";
            this.maxTokens = 100;
            this.temperature = 0.0;
            this.messages = new ArrayList<>();
            this.messages.add(new GptMessage("system", "You are a summarizer"));
            this.messages.add(new GptMessage("user", question));
        }
    }

    @Data
    @NoArgsConstructor
    public static class GptResponseDto {
        private String id;
        private String object;
        private long created;
        private String model;
        private List<GptChoice> choices;
        private Map<String, Integer> usage;

        @Builder
        public GptResponseDto(String id, String object, long created, String model, List<GptChoice> choices, Map<String, Integer> usage) {
            this.id = id;
            this.object = object;
            this.created = created;
            this.model = model;
            this.choices = choices;
            this.usage = usage;
        }

        @Data
        @NoArgsConstructor
        public static class GptChoice {
            private int index;
            private GptMessage message;
            private String finish_reason;
        }
    }


    @Data
    @NoArgsConstructor
    @Builder
    public static class GptMessage {
        private String role;
        private String content;

        public GptMessage(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}