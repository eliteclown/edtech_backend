package com.cybercity.application.repositories;

import com.cybercity.application.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity,Long> {
}
