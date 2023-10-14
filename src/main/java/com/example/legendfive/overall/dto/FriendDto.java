package com.example.legendfive.overall.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class FriendDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class FriendListResponseDto{
        private String friendNickname;
    }

//    @Getter
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//    public static class FriendListRequestDto{
//        private String friendNickname;
//    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class SearchFriendResponseDto{
        private String friendNickname;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class AddFriendRequsetDto{
        private String friendNickname;
        private UUID userUuId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class AddFriendResponseDto{
        private String message;
    }

}
