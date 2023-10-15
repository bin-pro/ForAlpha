package com.example.legendfive.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 Entity에 공통으로 포함 되어 있는 생성시간, 수정시간을 매핑해주는 클래스
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Time {

    @CreatedDate
    @Column(updatable = false,name = "created_at")
    private LocalDate createdAt;
}