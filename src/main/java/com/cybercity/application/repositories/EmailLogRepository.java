package com.cybercity.application.repositories;

import com.cybercity.application.entities.EmailLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLogEntity,Long> {
    List<EmailLogEntity> findByStatus(String status);
}
