package com.example.legendfive.overall.Entity;

import com.example.legendfive.common.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Column(name="stock_code")
    private Long stockCode;

    @Column(name = "stock_present_price")
    private int stock_present_price;

    @Column(name="end_day")
    private LocalDateTime endDay; // 예측 종료일

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "prediction_record_fk_user_id"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @JoinColumn(name = "feed_id", foreignKey = @ForeignKey(name = "prediction_record_fk_feed_id"))
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Feed feed;

    @JoinColumn(name = "stock_id", foreignKey = @ForeignKey(name = "prediction_record_fk_stock_id"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stock stock;
}
