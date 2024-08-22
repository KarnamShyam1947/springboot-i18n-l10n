package com.shyam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shyam.entities.ReviewTranslationEntity;
import java.util.List;


@Repository
public interface TranslationRepository extends JpaRepository<ReviewTranslationEntity, Integer> {
    List<ReviewTranslationEntity> findByLocale(String locale);
}
