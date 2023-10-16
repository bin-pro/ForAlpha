package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.PredictionRecord;
import com.example.legendfive.overall.dto.FeedDto;
import com.example.legendfive.overall.dto.FriendDto;
import com.example.legendfive.overall.repository.PredictionRecordRepository;
import com.example.legendfive.overall.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class FeedService {
    private final FriendService friendService;
    private final PredictionRecordRepository predictionRecordRepository;
    private final UserRepository userRepository;


    @Transactional
    public List<FeedDto.FriendFeedListResponseDto> getFriendPredictions(UUID userUuid) {
        List<FeedDto.FriendFeedListResponseDto> friendPredictions = new ArrayList<>();
        FriendDto.GetFriendsResponseDto friendsResponseDto = friendService.getFriendList(userUuid);

        for (Map<String, String> friendInfo : friendsResponseDto.getFriendList()) {
            UUID friendUuId = UUID.fromString(friendInfo.get("friend_id"));

            List<PredictionRecord> friendRecords = predictionRecordRepository.findAllByUserUserId(friendUuId);

            for (PredictionRecord record : friendRecords) {

                if (record.getStock_end_price() != 0 && record.isPublic()) {
                    FeedDto.FriendFeedListResponseDto friendFeed = FeedDto.FriendFeedListResponseDto.builder()
                            .friendNickname(friendInfo.get("friend_nickname"))
                            .stockName(record.getStock().getStockName())
                            .stockReturns(record.getStockIncreaseRate())
                            .beforePrice(record.getStock_present_price())
                            .afterPrice(record.getStock_end_price())
                            .build();

                    friendPredictions.add(friendFeed);
                }
            }
        }

        return friendPredictions;
    }

}
