package com.example.legendfive.overall.Entity;

import com.example.legendfive.common.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @Type(type = "uuid-char")
    @Column(name="prediction_record_uuid")
    private UUID predictionRecordUuid;

    @Column(name="stock_code")
    private String stockCode;

    @Column(name = "stock_present_price", columnDefinition = "int default 0") // 이거 은지언니랑 맞추기
    private int stock_present_price;

    @Column(name = "stock_end_price", columnDefinition = "int default 0")
    private int stock_end_price;  // 예측 종료일의 종가 -> endday에 맞춰서 값이 저장됨 ->

    @Column(name="end_day")
    private LocalDateTime endDay; // 예측 종료일

    @Column(name="stock_increaseRate")
    private String stockIncreaseRate;//->

    @ColumnDefault("true")
    @Column(name = "is_public", columnDefinition = "TINYINT(1)")
    private boolean isPublic;//------------------------------------------------

    @Column(name="earned_point")
    private int earnedPoint;//-----------------------------------------------

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "prediction_record_fk_user_id"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @JoinColumn(name = "stock_id", foreignKey = @ForeignKey(name = "prediction_record_fk_stock_id"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stock stock;

}
