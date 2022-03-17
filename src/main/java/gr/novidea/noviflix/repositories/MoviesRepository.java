package gr.novidea.noviflix.repositories;

import gr.novidea.noviflix.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoviesRepository extends JpaRepository <Movie, UUID> {

    Optional<Movie> findMovieByTitleEquals(String title);

}
