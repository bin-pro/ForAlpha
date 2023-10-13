//package com.example.legendfive.overall.Entity;
//
//import com.example.legendfive.common.Time;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.UUID;
//
//@Entity
//@Getter
//@Table(name = "theme")
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Theme extends Time {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "theme_id")
//    private Long id;
//
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name="uuid2", strategy = "uuid2")
//    @Column(name = "theme_uuid",columnDefinition = "BINARY(16)")
//    private UUID themeUuid;
//
//    @Column(name="stock_code")
//    private String stockCode;
//
//    @Column(name = "stock_name")
//    private String stockName;
//
//    @Column(name = "theme_name")
//    private String themeName;
//
//}
