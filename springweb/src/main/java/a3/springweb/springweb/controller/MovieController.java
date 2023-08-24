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
import org.springframework.boot.web.error.ErrorAttributeOptions;
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

import a3.springweb.springweb.mappers.CharacterMapper;
import a3.springweb.springweb.mappers.MovieMapper;
import a3.springweb.springweb.model.dtos.character.CharacterDTO;
import a3.springweb.springweb.model.dtos.movie.MovieDTO;
import a3.springweb.springweb.model.dtos.movie.MoviePostDTO;
import a3.springweb.springweb.model.dtos.movie.MovieUpdateDTO;
import a3.springweb.springweb.model.entities.Franchise;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.service.FranchiseService;
import a3.springweb.springweb.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {
    
    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper, CharacterMapper characterMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }

    @Operation(summary = "Get all movies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
        }),
        @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
})
    @GetMapping // GET: localhost:8080/api/v1/movies
    public ResponseEntity<Collection<MovieDTO>> getAll() {
        return ResponseEntity.ok(
            movieMapper.movieToMovieDto(
                movieService.findAll()));
    }

    @Operation(summary = "Get a movie by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Movie does not exist with the given ID", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }), 
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    public ResponseEntity<MovieDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(
            movieMapper.movieToMovieDto(
                movieService.findById(id)));
    }

    @Operation(summary = "Add a movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Movie created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
        }),
        @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping // POST: localhost:8080/api/v1/movies
    public ResponseEntity<MoviePostDTO> add(@RequestBody MoviePostDTO movieDto) {
        Movie mov = movieService.add(
            movieMapper.moviePostDtoToMovie(movieDto)
        );
        URI location = URI.create("movies/" + mov.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie successfully updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found with the given id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody MovieUpdateDTO movieDto, @PathVariable int id) {
        // Validates if body is correct
        if (id != movieDto.getId())
            return ResponseEntity.badRequest().build();
        movieService.update(
            movieMapper.movieUpdateDtoToMovie(movieDto)
            );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Malformed request"),
            @ApiResponse(responseCode = "404", description = "Movie not found with the given Id")
    })
    @OnDelete(action= OnDeleteAction.CASCADE)
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/students/1
    public ResponseEntity<Movie> delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update characters in a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Characters successfully updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404", description = "Movie not found with the given ID", content = @Content)
    })
    @PutMapping("characters/{id}")
    public ResponseEntity updateCharacters(@RequestBody int[] characterIds, @PathVariable int id) {
        movieService.updateCharacters(characterIds, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all characters in a franchise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class))) }),
            @ApiResponse(responseCode = "404", description = "Franchise does not exist with the given ID", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    @GetMapping("characters/{id}")
    public ResponseEntity<Collection<CharacterDTO>> getCharactersInFranchise(@PathVariable int id){
        return ResponseEntity.ok(
            characterMapper.characterToCharacterDto(
                movieService.displayCharacters(id)));
    }
    
}

