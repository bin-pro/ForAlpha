package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.Friends;
import com.example.legendfive.overall.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FriendRepository extends JpaRepository<Friends, Long> {
    List<Friends> findAllByUser1Id(Long user1Id);
    boolean existsByUser1IdAndUser2Id(Long user1Id, Long user2Id);

    int countFriendsByUser1Id(Long user1Id);
}
