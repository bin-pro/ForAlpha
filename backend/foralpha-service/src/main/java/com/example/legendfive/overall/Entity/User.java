package com.example.legendfive.overall.Entity;

import com.example.legendfive.common.Time;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @GeneratedValue(generator = "uuid2")
    @Column(name = "user_uuid")
    @Type(type = "uuid-char")
    private UUID userId;

    @Column(name = "nickname")
    private String nickname;

    @ColumnDefault("100")
    @Column(name = "user_point")
    private int userPoint;

    @OneToMany(mappedBy = "user")
    private List<PredictionRecord> predictionRecord;

    @OneToMany(mappedBy = "user")
    private List<QuizRecord> quizRecord;

    public void updateUserPoint(int userPoint){
        this.userPoint = userPoint;
    }

    @OneToMany(mappedBy = "user")
    private List<ThemeCard> themeCard;

    @Column(name = "user_invest_type")
    private String userInvestType;
}
