package gr.novidea.nietflix.services;

import gr.novidea.nietflix.entities.Movie;

import java.util.List;
import java.util.UUID;

public interface MoviesService {

    List<Movie> getMovies();

    Movie getMovie (UUID id);

    Movie addMovie (Movie movie);

    Boolean updateMovie (UUID id, Movie movie);

    Boolean deleteMovie (UUID id);

    Boolean findMovieByTitle(String title);

    Movie findMovieFromTitle(String title);

}
