package com.example.legendfive.overall.Entity;

import com.example.legendfive.common.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name = "quiz")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @GeneratedValue(generator = "uuid2")
    @Column(name = "quiz_uuid")
    @Type(type = "uuid-char")
    private UUID quizUuId;;

    @Column(name = "quiz_point")
    private int quizPoint;

    @Column(name = "quiz_question")
    private String quizQuestion;

    @Column(name = "quiz_answer")
    private String quizAnswer;

    @Column(name = "quiz_explanation")
    private String quizExplanation;

}
