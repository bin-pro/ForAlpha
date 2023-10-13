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
@Table(name = "stock")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "stock_uuid",columnDefinition = "BINARY(16)")
    private UUID stockUuid;

    @Column(name="stock_code")
    private String stockCode;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "theme_name")
    private String themeName;

    @Column(name = "stock_present_price")
    private int stockPresentPrice;

    @Column(name="stock_dod_percentage")
    private float stockDodPercentage;
}
