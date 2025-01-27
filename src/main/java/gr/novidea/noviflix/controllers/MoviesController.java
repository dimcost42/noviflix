package gr.novidea.noviflix.controllers;

import gr.novidea.noviflix.entities.Movie;
import gr.novidea.noviflix.services.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1")
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Operation(summary = "Get all movies", description = "Retrieve a list of all movies in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of movies retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
    })
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(moviesService.getMovies());
    }

    @Operation(summary = "Get movie by ID", description = "Retrieve a movie by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = moviesService.getMovie(id);
        return movie != null ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new movie", description = "Add a new movie to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "409", description = "Movie with the same title already exists")
    })
    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if (moviesService.existingMovieByTitle(movie.getTitle())) {
            Movie existingMovie = moviesService.findMovieFromTitle(movie.getTitle());
            return ResponseEntity.status(409).body(existingMovie);
        }
        Movie newMovie = moviesService.addMovie(movie);
        return ResponseEntity.status(201).body(newMovie);
    }

    @Operation(summary = "Delete a movie", description = "Delete a movie by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        return moviesService.deleteMovie(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update a movie", description = "Update the details of an existing movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "409", description = "Conflict with an existing movie title")
    })
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        Movie existingMovie = moviesService.getMovie(id);
        if (existingMovie == null) {
            return ResponseEntity.notFound().build();
        }

        Movie movieWithSameTitle = moviesService.findMovieFromTitle(movie.getTitle());
        if (movieWithSameTitle != null && !movieWithSameTitle.getId().equals(id)) {
            return ResponseEntity.status(409).body(existingMovie);
        }

        boolean isUpdated = moviesService.updateMovie(id, movie);
        return isUpdated ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get a random movie", description = "Retrieve a random movie from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Random movie retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "404", description = "No movies available")
    })
    @GetMapping("/movies/whatsnext")
    public ResponseEntity<Movie> getRandomMovie() {
        List<Movie> movieList = moviesService.getMovies();
        if (movieList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Movie randomMovie = movieList.get(ThreadLocalRandom.current().nextInt(movieList.size()));
        return ResponseEntity.ok(randomMovie);
    }

    @Operation(summary = "Load sample movies", description = "Load a set of predefined movies into the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sample movies loaded successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
    })
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
