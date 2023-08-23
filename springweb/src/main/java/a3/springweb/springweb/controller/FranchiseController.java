package a3.springweb.springweb.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import a3.springweb.springweb.mappers.CharacterMapper;
import a3.springweb.springweb.mappers.FranchiseMapper;
import a3.springweb.springweb.mappers.MovieMapper;
import a3.springweb.springweb.model.dtos.character.CharacterDTO;
import a3.springweb.springweb.model.dtos.franchise.FranchiseDTO;
import a3.springweb.springweb.model.dtos.franchise.FranchisePostDTO;
import a3.springweb.springweb.model.dtos.franchise.FranchiseUpdateDTO;
import a3.springweb.springweb.model.dtos.movie.MovieDTO;
import a3.springweb.springweb.model.entities.Franchise;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {
    
    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final CharacterMapper characterMapper;
    private final MovieMapper movieMapper;

    @Autowired
    public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper, CharacterMapper characterMapper, MovieMapper movieMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.characterMapper = characterMapper;
        this.movieMapper = movieMapper;
    }

    @Operation(summary = "Get all franchises")
    @GetMapping // GET: localhost:8080/api/v1/movies
    public ResponseEntity<Collection<FranchiseDTO>> getAll() {
        return ResponseEntity.ok(
            franchiseMapper.franchiseToFranchiseDto(
                franchiseService.findAll()));
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/franchises/1
    public ResponseEntity<FranchiseDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(
            franchiseMapper.franchiseToFranchiseDto(
                franchiseService.findById(id))
            );
    }

    @PostMapping // POST: localhost:8080/api/v1/franchises
    public ResponseEntity<FranchisePostDTO> add(@RequestBody FranchisePostDTO franchiseDto) {
        Franchise fran = franchiseService.add(
            franchiseMapper.franchisePostDtoToFranchise(franchiseDto)
            );
        URI location = URI.create("franchises/" + fran.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody FranchiseUpdateDTO franchiseDto, @PathVariable int id) {
        // Validates if body is correct
        if (id != franchiseDto.getId())
            return ResponseEntity.badRequest().build();
        franchiseService.update(
            franchiseMapper.franchiseUpdateDtoToFranchise(franchiseDto)
        );
        return ResponseEntity.noContent().build();
    }

    @OnDelete(action= OnDeleteAction.CASCADE)
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/students/1
    public ResponseEntity<Franchise> delete(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("movies/{id}")
    public ResponseEntity updateMovies(@RequestBody int[] movieIds, @PathVariable int id) {
        franchiseService.updateMovies(movieIds, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("characters/{id}")
    public ResponseEntity<Collection<CharacterDTO>> getCharactersInFranchise(@PathVariable int id){
        return ResponseEntity.ok(
            characterMapper.characterToCharacterDto(
            franchiseService.displayCharacters(id))
        );
    }

    @GetMapping("movies/{id}")
    public ResponseEntity<Collection<MovieDTO>> getMoviesInFranchise(@PathVariable int id){
        return ResponseEntity.ok(
            movieMapper.movieToMovieDto(
                franchiseService.displayMovies(id))
            );
    }
}
