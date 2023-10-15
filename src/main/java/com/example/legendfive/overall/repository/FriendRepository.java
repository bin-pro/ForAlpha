package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.Friend;
import com.example.legendfive.overall.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByUser1Id(UUID user1Id);

    boolean existsByUser1IdAndUser2Id(UUID user1Id, UUID user2Id);
    int countFriendsByUser1Id(UUID user1Id);
}
