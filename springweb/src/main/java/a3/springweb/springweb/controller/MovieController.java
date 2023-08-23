package a3.springweb.springweb.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import a3.springweb.springweb.mappers.MovieMapper;
import a3.springweb.springweb.model.dtos.movie.MovieDTO;
import a3.springweb.springweb.model.dtos.movie.MoviePostDTO;
import a3.springweb.springweb.model.dtos.movie.MovieUpdateDTO;
import a3.springweb.springweb.model.entities.Franchise;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.service.FranchiseService;
import a3.springweb.springweb.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
    
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @Operation(summary = "Get all movies")
    @GetMapping // GET: localhost:8080/api/v1/movies
    public ResponseEntity<Collection<MovieDTO>> getAll() {
        return ResponseEntity.ok(
            movieMapper.movieToMovieDto(
                movieService.findAll()));
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    public ResponseEntity<MovieDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(
            movieMapper.movieToMovieDto(
                movieService.findById(id)));
    }

    @PostMapping // POST: localhost:8080/api/v1/movies
    public ResponseEntity<MoviePostDTO> add(@RequestBody MoviePostDTO movieDto) {
        Movie mov = movieService.add(
            movieMapper.moviePostDtoToMovie(movieDto)
        );
        URI location = URI.create("movies/" + mov.getId());
        return ResponseEntity.created(location).build();
    }

    
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Movie movie, @PathVariable int id) {
        // Validates if body is correct
        if (id != movie.getId())
            return ResponseEntity.badRequest().build();
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @OnDelete(action= OnDeleteAction.CASCADE)
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/students/1
    public ResponseEntity<Movie> delete(@PathVariable int id) {
        System.out.println("id: " + id);
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("characters/{id}")
    public ResponseEntity updateCharacters(@RequestBody int[] characterIds, @PathVariable int id) {
        System.out.println("movie id: " + id + " chr ids: " + Arrays.toString(characterIds));
        movieService.updateCharacters(characterIds, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("characters/{id}")
    public ResponseEntity<Collection<MovieCharacter>> getCharactersInFranchise(@PathVariable int id){
        return ResponseEntity.ok(movieService.displayCharacters(id));
    }
    
}

