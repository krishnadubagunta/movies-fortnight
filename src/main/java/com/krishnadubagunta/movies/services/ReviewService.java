package com.krishnadubagunta.movies.services;

import com.krishnadubagunta.movies.models.Movie;
import com.krishnadubagunta.movies.models.Review;
import com.krishnadubagunta.movies.repositories.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    public ReviewService(final ReviewRepository rP, final MongoTemplate mT) {
        this.reviewRepository = rP;
        this.mongoTemplate = mT;
    }

    public Review createReview(String body, String imdbId) {
        Review review = reviewRepository.insert(new Review(body));
        mongoTemplate.update(Movie.class)
                    .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }

    public List<Review> getReviewByIds(List<ObjectId> ids) {
        return reviewRepository.findAllById(ids);
    }

}
