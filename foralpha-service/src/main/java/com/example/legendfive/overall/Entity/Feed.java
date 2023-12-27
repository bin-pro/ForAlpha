package com.example.legendfive.overall.Entity;

import com.example.legendfive.common.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;

@Entity
@Getter
@Table(name = "feeds")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feed extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    @Column(name = "buying_price")
    private int buyingPrice;

    @Column(name = "selling_price")
    private int sellingPirce;

    @ColumnDefault("true")
    @Column(name = "is_public", columnDefinition = "TINYINT(1)")
    private boolean isPublic;

    @Column(name="earned_point")
    private int earnedPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prediction_record_id")
    private PredictionRecord predictionRecord;
}
