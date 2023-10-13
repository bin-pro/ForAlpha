package com.example.legendfive.overall.Entity;


import com.example.legendfive.common.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name = "feed")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feed extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "prediction_record_uuid",columnDefinition = "BINARY(16)")
    private UUID predictionRecordUuid;

    @Column(name = "buying_price")
    private int buytingPrice;
    @Column(name = "selling_price")
    private int sellingPirce;
    @Column(name = "is_public")
    private int isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prediction_record_id")
    private PredictionRecord predictionRecord;
}
