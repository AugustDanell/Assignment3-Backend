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


    /**
     * getAll()
     * Maps to a GET request.
     * Returns all franchises in the data base.
     * @return A response entity with the collection of every franchise in the database.
     */

    @Operation(summary = "Get all franchises")
    @GetMapping // GET: localhost:8080/api/v1/movies
    public ResponseEntity<Collection<FranchiseDTO>> getAll() {
        return ResponseEntity.ok(
            franchiseMapper.franchiseToFranchiseDto(
                franchiseService.findAll()));
    }

    /**
     * getById()
     * Maps to a GET request.
     * Takes in an ID and returns a FranciseDTO corresponding to that id.
     * @param id
     * @return The response entity with a franchiseDTO, corresponding to id.
     */

    @GetMapping("{id}") // GET: localhost:8080/api/v1/franchises/1
    public ResponseEntity<FranchiseDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(
            franchiseMapper.franchiseToFranchiseDto(
                franchiseService.findById(id))
            );
    }

    /**
     * add()
     * Maps to a POST request.
     * Adds a Franchise to the database.
     * @param franchiseDto, A franchiseDTO that is inserted in the body of the request.
     * @return A response entity, corresponding to the success of the creation of a franchise.
     */

    @PostMapping // POST: localhost:8080/api/v1/franchises
    public ResponseEntity<FranchisePostDTO> add(@RequestBody FranchisePostDTO franchiseDto) {
        Franchise fran = franchiseService.add(
            franchiseMapper.franchisePostDtoToFranchise(franchiseDto)
            );
        URI location = URI.create("franchises/" + fran.getId());
        return ResponseEntity.created(location).build();
    }

    /**
     * update()
     * Maps to a PUT request.
     * Updates a franchise in the database.
     * @param franchiseDto, A franchiseDTO that corresponds to a character that is to be updated.
     * @param id, The id of the character object to be updated.
     * @return The success of the updated.
     */

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


    /**
     * 
     * @param id
     * @return
     */

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
