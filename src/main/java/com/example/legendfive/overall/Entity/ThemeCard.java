package com.example.legendfive.overall.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "theme_cards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThemeCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_card_id")
    private Long id;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "theme_card_fk_user_id"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @Column(name = "theme_name")
    private String themeName;

    @Column(name = "theme_count")
    private Long themeCount;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
