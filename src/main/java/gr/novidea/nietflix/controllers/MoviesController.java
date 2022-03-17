package gr.novidea.nietflix.controllers;

import gr.novidea.nietflix.entities.Movie;
import gr.novidea.nietflix.repositories.MoviesRepository;
import gr.novidea.nietflix.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class MoviesController {

    @Autowired
    MoviesService moviesService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = moviesService.getMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") UUID id) {
        Movie movie = moviesService.getMovie(id);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie m = moviesService.addMovie(movie);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Movie", "/api/v1/movies/" + m.getId().toString());
        return new ResponseEntity<>(m, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable("id") UUID id) {
        if (moviesService.deleteMovie(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> udpateMovie(@RequestBody Movie movie, @PathVariable("id") UUID id) {

        if (moviesService.updateMovie(id, movie)) {
            return new ResponseEntity<>(movie, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/movies/whatsnext")
    public ResponseEntity<Movie> getRandomMovie() {
        List<Movie> movieList = moviesService.getMovies();
        if (movieList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Movie movie = movieList.get(new Random().nextInt(movieList.size()));
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

}
