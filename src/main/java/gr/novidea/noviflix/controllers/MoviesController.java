package gr.novidea.noviflix.controllers;

import gr.novidea.noviflix.entities.Movie;
import gr.novidea.noviflix.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        HttpHeaders httpHeaders = new HttpHeaders();

        if (moviesService.findMovieByTitle(movie.getTitle())) {
            Movie movie1 = moviesService.findMovieFromTitle(movie.getTitle());
            httpHeaders.add("Movie already exists", "/api/v1/movies/" + movie1.getId().toString());
            return new ResponseEntity<>(movie1, httpHeaders, HttpStatus.CONFLICT);
        }

        Movie m = moviesService.addMovie(movie);
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

        if (moviesService.findMovieByTitle(movie.getTitle())) {
            Movie movie1 = moviesService.findMovieFromTitle(movie.getTitle());
            return new ResponseEntity<>(movie1, HttpStatus.CONFLICT);
        }

        if (moviesService.updateMovie(id, movie)) {
            return new ResponseEntity<>(movie, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/movies/whatsnext")
    public ResponseEntity<Movie> getRandomMovie() {
        List<Movie> movieList = moviesService.getMovies();
        if (movieList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Movie movie = movieList.get(new Random().nextInt(movieList.size()));
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/loadmovies")
    public ResponseEntity<List<Movie>> initializeData() {

        ArrayList<Movie> moviesList = new ArrayList<>();

        Movie movie = new Movie();
        movie.setTitle("Memento");
        movie.setDirector("Christopher Nolan");
        movie.setPlot("A man with short-term memory loss attempts to track down his wife's murderer.");

        Movie movie2 = new Movie();
        movie2.setTitle("Shutter Island");
        movie2.setDirector("Martin Scorsese");
        movie2.setPlot("In 1954, a U.S. Marshal investigates the disappearance of a murderer who escaped from a hospital for the criminally insane.");

        Movie movie3 = new Movie();
        movie3.setTitle("Pulp Fiction");
        movie3.setDirector("Quentin Tarantino");
        movie3.setPlot("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.");

        Movie movie4 = new Movie();
        movie4.setTitle("The Fountain");
        movie4.setDirector("Darren Aronofsky");
        movie4.setPlot("As a modern-day scientist, Tommy is struggling with mortality, desperately searching for the medical breakthrough that will save the life of his cancer-stricken wife, Izzi.");

        Movie movie5 = new Movie();
        movie5.setTitle("Melancholia");
        movie5.setDirector("Lars von Trier");
        movie5.setPlot("Two sisters find their already strained relationship challenged as a mysterious new planet threatens to collide with Earth.");

        Movie movie6 = new Movie();
        movie6.setTitle("Angel's Egg");
        movie6.setDirector("Mamoru Oshii");
        movie6.setPlot("A mysterious young girl wanders a desolate, otherworldly landscape, carrying a large egg.");

        Movie movie7 = new Movie();
        movie7.setTitle("Perfect Blue");
        movie7.setDirector("Satoshi Kon");
        movie7.setPlot("A pop singer gives up her career to become an actress, but she slowly goes insane when she starts being stalked by an obsessed fan and what seems to be a ghost of her past.");

        moviesList.add(movie);
        moviesList.add(movie2);
        moviesList.add(movie3);
        moviesList.add(movie4);
        moviesList.add(movie5);
        moviesList.add(movie6);
        moviesList.add(movie7);

        moviesList.forEach(moviesService::addMovie);

        return new ResponseEntity<>(moviesService.getMovies(), HttpStatus.OK);
    }

}
