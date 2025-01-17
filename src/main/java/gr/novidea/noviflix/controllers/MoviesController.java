package gr.novidea.noviflix.controllers;

import gr.novidea.noviflix.entities.Movie;
import gr.novidea.noviflix.services.MoviesService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1")
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = moviesService.getMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = moviesService.getMovie(id);
        return movie == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(movie);
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if (moviesService.findMovieByTitle(movie.getTitle())) {
            Movie existingMovie = moviesService.findMovieFromTitle(movie.getTitle());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Movie already exists", "/api/v1/movies/" + existingMovie.getId());
            return new ResponseEntity<>(existingMovie, headers, HttpStatus.CONFLICT);
        }

        Movie newMovie = moviesService.addMovie(movie);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/movies/" + newMovie.getId());
        return new ResponseEntity<>(newMovie, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable("id") Long id) {
        return moviesService.deleteMovie(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable("id") Long id) {
        if (moviesService.findMovieByTitle(movie.getTitle())) {
            Movie existingMovie = moviesService.findMovieFromTitle(movie.getTitle());
            return new ResponseEntity<>(existingMovie, HttpStatus.CONFLICT);
        }

        boolean updated = moviesService.updateMovie(id, movie);
        return updated ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
    }

    @GetMapping("/movies/whatsnext")
    public ResponseEntity<Movie> getRandomMovie() {
        List<Movie> movieList = moviesService.getMovies();
        if (movieList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Movie randomMovie = movieList.get(ThreadLocalRandom.current().nextInt(movieList.size()));
        return ResponseEntity.ok(randomMovie);
    }

    @GetMapping("/loadmovies")
    public ResponseEntity<List<Movie>> initializeData() {
        List<Movie> moviesList = Arrays.asList(
                new Movie("Memento", "Christopher Nolan", "A man with short-term memory loss attempts to track down his wife's murderer."),
                new Movie("Shutter Island", "Martin Scorsese", "In 1954, a U.S. Marshal investigates the disappearance of a murderer who escaped from a hospital for the criminally insane."),
                new Movie("Pulp Fiction", "Quentin Tarantino", "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."),
                new Movie("The Fountain", "Darren Aronofsky", "As a modern-day scientist, Tommy is struggling with mortality, desperately searching for the medical breakthrough that will save the life of his cancer-stricken wife, Izzi."),
                new Movie("Melancholia", "Lars von Trier", "Two sisters find their already strained relationship challenged as a mysterious new planet threatens to collide with Earth."),
                new Movie("Angel's Egg", "Mamoru Oshii", "A mysterious young girl wanders a desolate, otherworldly landscape, carrying a large egg."),
                new Movie("Perfect Blue", "Satoshi Kon", "A pop singer gives up her career to become an actress, but she slowly goes insane when she starts being stalked by an obsessed fan and what seems to be a ghost of her past.")
        );

        moviesList.forEach(moviesService::addMovie);
        return ResponseEntity.ok(moviesService.getMovies());
    }
}
