package gr.novidea.nietflix.services;

import gr.novidea.nietflix.entities.Movie;
import gr.novidea.nietflix.repositories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    MoviesRepository moviesRepository;

    @Override
    public List<Movie> getMovies() {
        return moviesRepository.findAll();
    }

    @Override
    public Movie getMovie(UUID id) {
        Optional<Movie> movieOptional = moviesRepository.findById(id);
        return movieOptional.orElse(null);
    }

    @Override
    public Movie addMovie(Movie movie) {
        Optional<Movie> isMovieExists = moviesRepository.findMovieByTitleEquals(movie.getTitle());
        return isMovieExists.orElseGet(() -> moviesRepository.saveAndFlush(movie));
    }

    @Override
    public Boolean updateMovie(UUID id, Movie movie) {
        Optional<Movie> movieOptional = moviesRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie m = movieOptional.get();
            m.setDirector(movie.getDirector());
            m.setPlot(movie.getPlot());
            m.setTitle(movie.getTitle());
            moviesRepository.saveAndFlush(movie);
            return true;
        }

        return false;
    }

    @Override
    public Boolean deleteMovie(UUID id) {
        moviesRepository.deleteById(id);
        return moviesRepository.findById(id).isEmpty();
    }

    @Override
    public Boolean findMovieByTitle(String title) {
        Optional<Movie> isMovieExists = moviesRepository.findMovieByTitleEquals(title);
        return isMovieExists.isPresent();
    }

    @Override
    public Movie findMovieFromTitle(String title) {
        Optional<Movie> isMovieExists = moviesRepository.findMovieByTitleEquals(title);
        if (isMovieExists.isPresent()){
            return isMovieExists.get();
        }
        return null;
    }
}
