package gr.novidea.noviflix.services;

import gr.novidea.noviflix.entities.Movie;

import java.util.List;

public interface MoviesService {

    List<Movie> getMovies();

    Movie getMovie (Long id);

    Movie addMovie (Movie movie);

    Boolean updateMovie (Long id, Movie movie);

    Boolean deleteMovie (Long id);

    Boolean existingMovieByTitle(String title);

    Movie findMovieFromTitle(String title);

}
