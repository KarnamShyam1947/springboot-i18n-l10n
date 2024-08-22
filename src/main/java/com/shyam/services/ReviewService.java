package com.shyam.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.shyam.dto.Result;
import com.shyam.dto.ReviewDTO;
import com.shyam.entities.ReviewEntity;
import com.shyam.entities.ReviewTranslationEntity;
import com.shyam.repositories.ReviewRepository;
import com.shyam.repositories.TranslationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private static final String[] languages = {"fr", "hi", "te", "it", "ta"};

    private final TestService service;
    private final LocaleService localeService;
    private final ReviewRepository reviewRepository;
    private final TranslationRepository translationRepository;

    private static ReviewDTO getText(String str) {
        String[] split = str.split("\\*");

        ReviewDTO review = new ReviewDTO();
        review.setUsername(split[0]);
        review.setTitle(split[1]);
        review.setDescription(split[2]);

        return review;
    }

    public void addReview(ReviewDTO review) {
        String queryString = review.getUsername()+"*"+review.getTitle()+"*"+review.getDescription();

        Set<ReviewTranslationEntity> translations = new HashSet<>();
        Result translate = service.translate(queryString);

        for (String locale : languages) {
            ReviewTranslationEntity translation = new ReviewTranslationEntity();
            ReviewDTO translatedReview = new ReviewDTO();

            if (locale.equals("fr")) 
                translatedReview = getText(translate.getFr());
            
            if (locale.equals("hi")) 
                translatedReview = getText(translate.getHi());
            
            if (locale.equals("it")) 
                translatedReview = getText(translate.getIt());
            
            if (locale.equals("ta")) 
                translatedReview = getText(translate.getTa());
            
            if (locale.equals("te")) 
                translatedReview = getText(translate.getTe());

            translation.setLocale(locale);
            translation.setTitle(translatedReview.getTitle());
            translation.setUsername(translatedReview.getUsername());
            translation.setDescription(translatedReview.getDescription());
            

            translations.add(translation);
        }

        translationRepository.saveAll(translations);

        ReviewEntity reviewEntity = ReviewEntity
                                        .builder()
                                        .createdAt(LocalDateTime.now())
                                        .updatedAt(LocalDateTime.now())
                                        .translations(translations)
                                        .build();

        reviewRepository.save(reviewEntity);
    }
    
    public List<ReviewTranslationEntity> getReviews() {
        return translationRepository.findByLocale(localeService.getLocale());
    }
}
