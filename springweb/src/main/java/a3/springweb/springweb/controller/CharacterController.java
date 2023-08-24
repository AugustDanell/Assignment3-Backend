package a3.springweb.springweb.controller;

import java.net.URI;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import a3.springweb.springweb.mappers.CharacterMapper;
import a3.springweb.springweb.model.dtos.character.CharacterDTO;
import a3.springweb.springweb.model.dtos.character.CharacterPostDTO;
import a3.springweb.springweb.model.dtos.character.CharacterUpdateDTO;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    /**
     * getAll()
     * A controller function that maps a URL to the findAll function, via a characterDTO.
     * With the characterDTO we can controll what is to be displayed.
     * @return A response entity that is a collection of Characters (DTOs)
     */

    @Operation(summary = "Get all characters")
    @GetMapping // GET: localhost:8080/api/v1/movies
    public ResponseEntity<Collection<CharacterDTO>> getAll() {
        return ResponseEntity.ok(
            characterMapper.characterToCharacterDto(
                characterService.findAll())
            );
    }


    /**
     * getById()
     * Maps a GET reqeusts.
     * Returns a response entity in the form of a character corresponding to an ID.
     * @param id, The id corresponding to a character in the database
     * @return The response DTO entity.
     */

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    public ResponseEntity<CharacterDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(
            characterMapper.characterToCharacterDto(
                characterService.findById(id))
            );
    }

    /**
     * add()
     * Maps a POST request.
     * A character is inserted into the database via the POST method.
     * @param character, The characterDTO entity that is inserted.
     * @return The response entity.
     */

    @PostMapping // POST: localhost:8080/api/v1/movies
    public ResponseEntity<CharacterPostDTO> add(@RequestBody CharacterPostDTO character) {
        MovieCharacter movieCharacter = characterService.add(
            characterMapper.characterPostDtoToCharacter(character)
        );
        URI location = URI.create("characters/" + movieCharacter.getId());
        return ResponseEntity.created(location).build();
    }

    /**
     * Maps a PUT request.
     * A character is updated, given that the pathID match with the id in the body.
     * @param characterDto, The DTO for a character that is to be updated
     * @param id, An id corresponding to the character
     * @return The ResponseEntity
     */

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody CharacterUpdateDTO characterDto, @PathVariable int id) {
        // Validates if body is correct
        if (id != characterDto.getId())
            return ResponseEntity.badRequest().build();
        characterService.update(
            characterMapper.characterUpdateDtoToCharacter(characterDto)
        );
        return ResponseEntity.noContent().build();
    }

    /**
     * delete()
     * Maps a DELETE request.
     * Takes in an ID and removes the character with that id.
     * @param id
     * @return
     */
    
    @OnDelete(action= OnDeleteAction.CASCADE)
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/students/1
    public ResponseEntity<MovieCharacter> delete(@PathVariable int id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

