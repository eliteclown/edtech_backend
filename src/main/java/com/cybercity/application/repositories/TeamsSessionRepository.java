package com.cybercity.application.repositories;

import com.cybercity.application.entities.TeamsSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsSessionRepository extends JpaRepository<TeamsSessionEntity,Long> {
}
