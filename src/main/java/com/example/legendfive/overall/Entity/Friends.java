package com.example.legendfive.overall.Entity;

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
@Table(name = "friends")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friends_id")
    private Long id;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "friends_uuid",columnDefinition = "BINARY(16)")
    private UUID friendsUuid;

    @Column(name = "user1_id")
    @NotNull
    private String user1Id;

    @Column(name = "user2_id")
    @NotNull
    private String user2Id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
