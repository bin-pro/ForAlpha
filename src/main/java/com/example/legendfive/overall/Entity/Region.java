package com.example.legendfive.overall.Entity;

import com.example.legendfive.common.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Table(name = "region")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name="region_uuid",columnDefinition = "BINARY(16)")
    private UUID regionUuid;

    @Column(name = "region_name")
    @NotNull
    private String region_name;
}
