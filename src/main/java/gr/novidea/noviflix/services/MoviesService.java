package gr.novidea.noviflix.services;

import gr.novidea.noviflix.entities.Movie;

import java.util.List;
import java.util.UUID;

public interface MoviesService {

    List<Movie> getMovies();

    Movie getMovie (Long id);

    Movie addMovie (Movie movie);

    Boolean updateMovie (Long id, Movie movie);

    Boolean deleteMovie (Long id);

    Boolean findMovieByTitle(String title);

    Movie findMovieFromTitle(String title);

}
