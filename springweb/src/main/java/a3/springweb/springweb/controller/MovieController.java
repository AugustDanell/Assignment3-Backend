package a3.springweb.springweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import a3.springweb.springweb.model.Franchise;
import a3.springweb.springweb.model.Movie;
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

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    public ResponseEntity<Movie> getById(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id));
    }
}

