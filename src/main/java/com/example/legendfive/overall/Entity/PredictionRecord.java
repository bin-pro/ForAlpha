package com.example.legendfive.overall.Entity;

import com.example.legendfive.common.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Table(name = "prediction_records")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredictionRecord extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prediction_record_id")
    private Long id;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "prediction_record_uuid",columnDefinition = "BINARY(16)")
    private UUID predictionRecordUuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name="stock_code")
    private Long stockCode;

    @Column(name = "stock_present_price")
    private int stock_present_price;

    @Column(name="end_day")
    private LocalDateTime endDay; // 예측 종료일

    @Column(name="earning_point")
    private int earnedPoint;

    public void updateEarningPoint(int earnedPoint){
        this.earnedPoint = earnedPoint;
    }

}
