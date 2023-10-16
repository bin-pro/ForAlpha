package com.example.legendfive.overall.Entity;

import com.example.legendfive.common.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Type(type = "uuid-char")
    @Column(name="prediction_record_uuid")
    private UUID predictionRecordUuid;

    @Column(name="stock_code")
    private String stockCode;

    @Column(name = "stock_present_price")
    private int stockPresentPrice;

    @Column(name="stock_end_price")
    private int stockEndPrice;

    @Column(name="end_day")
    private LocalDate endDay;

    @ColumnDefault("true")
    @Column(name = "is_public", columnDefinition = "TINYINT(1)")
    private boolean isPublic;

    //포인트
    @ColumnDefault("0")
    @Column(name="stock_earned_point")
    private String stockEarnedPoint;

    //수익률
    @ColumnDefault("0")
    @Column(name="stock_increase_rate")
    private String stockIncreaseRate;


    @Column(name="earned_point")
    private int earnedPoint;//-----------------------------------------------

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "prediction_record_fk_user_id"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @JoinColumn(name = "stock_id", foreignKey = @ForeignKey(name = "prediction_record_fk_stock_id"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stock stock;

    public void updateStockEndPriceIncreateRate(int stockEndPrice, String stockIncreaseRate, String stockEarnedPoint){
        this.stockEndPrice = stockEndPrice;
        this.stockIncreaseRate = stockIncreaseRate;
        this.stockEarnedPoint = stockEarnedPoint;
    }
}
