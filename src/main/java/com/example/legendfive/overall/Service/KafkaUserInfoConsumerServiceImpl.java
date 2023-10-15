/*package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.UserDto;
import com.example.legendfive.overall.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserInfoConsumerServiceImpl implements KafkaUserInfoConsumerService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    @KafkaListener(topics = "user-create")
    @Transactional
    public void createUser(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);

        UserDto.UserInfoConsumerDto userInfoConsumerDto;
        try {
            userInfoConsumerDto = objectMapper.readValue(payload, UserDto.UserInfoConsumerDto.class);
        } catch (Exception e) {
            log.error("Error converting payload to uploader dto", e);
            return;
        }

        User user;
        try {
            user = modelMapper.map(uploaderDto, Uploader.class);
        } catch (Exception e) {
            log.error("Error mapping uploader dto to uploader", e);
            return;
        }

        try {
            uploaderRepository.save(uploader);
        } catch (Exception e) {
            log.error("Error saving uploader", e);
        }

        acknowledgment.acknowledge();
    }

    @Override
    @KafkaListener(topics = "uploader-update")
    @Transactional
    public void updateUploader(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);

        UploaderDto.UploaderModifyRequestDto uploaderModifyRequestDto;
        try {
            uploaderModifyRequestDto = objectMapper.readValue(payload, UploaderDto.UploaderModifyRequestDto.class);
        } catch (Exception e) {
            log.error("Error converting payload to uploader dto", e);
            return;
        }

        String sellerId = uploaderModifyRequestDto.getSellerId();
        Uploader uploader;
        try {
            uploader = uploaderRepository.findBySellerId(sellerId).orElseThrow(() -> new NoSuchElementException("uploader not found"));
        } catch (Exception e) {
            log.error("Uploader not found", e);
            return;
        }

        try {
            uploader.update(uploaderModifyRequestDto);
        } catch (Exception e) {
            log.error("Error updating uploader", e);
        }

        List<Video> uploaderVideos = uploader.getVideos();

        try {
            uploaderVideos.stream().forEach(
                    video -> {
                        DenormalizedVideoDto denormalizedVideoDto = DenormalizedVideoDto.builder()
                                .videoId(video.getVideoId())
                                .sellerId(uploader.getSellerId())
                                .sellerLogo(Base64.getEncoder().encodeToString(uploader.getSellerLogo()))
                                .sellerName(uploader.getSellerName())
                                .build();
                        kafkaVideoInfoProducerService.updateVideo(denormalizedVideoDto);
                    }
            );
        } catch (Exception e) {
            log.error("Error publishing updated video", e);
        }

        acknowledgment.acknowledge();
    }

    @Override
    @KafkaListener(topics = "uploader-delete")
    @Transactional
    public void deleteUploader(String payload, Acknowledgment acknowledgment) {
        log.info("received payload='{}'", payload);

        String sellerId = payload;

        Uploader uploader;
        try {
            uploader = uploaderRepository.findBySellerId(sellerId).orElseThrow(() -> new NoSuchElementException("uploader not found"));
        } catch (Exception e) {
            log.error("Uploader not found", e);
            return;
        }

        List<String> uploaderVideoIds = uploader.getVideos().stream().map(Video::getVideoId).collect(Collectors.toList());

        uploaderVideoIds.forEach(videoId -> {kafkaVideoInfoProducerService.deleteVideo(videoId);});

        try {
            uploaderRepository.delete(uploader);
        } catch (Exception e) {
            log.error("Error deleting uploader", e);
        }

        acknowledgment.acknowledge();
    }
}

 */
