package a3.springweb.springweb.controller;

import java.net.URI;
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

import a3.springweb.springweb.model.Franchise;
import a3.springweb.springweb.model.Movie;
import a3.springweb.springweb.model.MovieCharacter;
import a3.springweb.springweb.service.FranchiseService;
import a3.springweb.springweb.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
    
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get all movies")
    @GetMapping // GET: localhost:8080/api/v1/movies
    public ResponseEntity<Collection<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    public ResponseEntity<Movie> getById(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping // POST: localhost:8080/api/v1/movies
    public ResponseEntity<Movie> add(@RequestBody Movie movie) {
        Movie mov = movieService.add(movie);
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

    @PutMapping("update_characters/{id}")
    public ResponseEntity updateCharacters(@RequestBody Movie movie, @PathVariable int id) {
    return ResponseEntity.noContent().build();
    }
    
}

