package com.krishnadubagunta.movies.services;

import com.krishnadubagunta.movies.models.Movie;
import com.krishnadubagunta.movies.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(final MovieRepository mr) {
        this.movieRepository = mr;
    }
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> movieById(ObjectId id) {
        return  movieRepository.findById(id);
    }

    public Optional<Movie> movieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
