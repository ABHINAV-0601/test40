package com.michaels.designhub.repository;

import com.michaels.designhub.entity.TrainingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;

public interface TrainingLogRepository extends JpaRepository<TrainingLog,Integer> {

    @Modifying
    @Query("update TrainingLog t set t.exitedAt = ?1 where id = ?2")
    void updateExitedAtBy(LocalDateTime now, Integer id);
}
