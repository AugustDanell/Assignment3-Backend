package a3.springweb.springweb.controller;

import java.net.URI;
import java.util.Collection;

// SpringBoot and Hibernate imports:
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

// Internal imports:
import a3.springweb.springweb.mappers.CharacterMapper;
import a3.springweb.springweb.model.dtos.character.CharacterDTO;
import a3.springweb.springweb.model.dtos.character.CharacterPostDTO;
import a3.springweb.springweb.model.dtos.character.CharacterUpdateDTO;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.service.character.CharacterService;

// Swagger annotations import (For API Documentation)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
     * Maps a GET request.
     * A controller function that maps a URL to the findAll function, via a characterDTO.
     * With the characterDTO we can control what is to be displayed.
     * @return A response entity that is a collection of Characters (DTOs)
     */

    @Operation(summary = "Get all characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping // GET: localhost:8080/api/v1/character
    public ResponseEntity<Collection<CharacterDTO>> getAll() {
        return ResponseEntity.ok(
            characterMapper.characterToCharacterDto(
                characterService.findAll())
            );
    }


    /**
     * getById()
     * Maps a GET request.
     * Returns a response entity in the form of a character corresponding to an ID.
     * @param id, The id corresponding to a character in the database
     * @return The response DTO entity.
     */

    @Operation(summary = "Get a character by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Movie does not exist with the given ID", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }), 
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("{id}") // GET: localhost:8080/api/v1/characters/1
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

    @Operation(summary = "Add a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Character created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping // POST: localhost:8080/api/v1/characters
    public ResponseEntity<CharacterPostDTO> add(@RequestBody CharacterPostDTO character) {
        MovieCharacter movieCharacter = characterService.add(
                characterMapper.characterPostDtoToCharacter(character));
        URI location = URI.create("characters/" + movieCharacter.getId());
        return ResponseEntity.created(location).build();
    }

    /**
     * update()
     * Maps a PUT request.
     * A character is updated, given that the pathID match with the id in the body.
     * @param characterDto, The DTO for a character that is to be updated
     * @param id, An id corresponding to the character.
     * @return The ResponseEntity.
     */

    @Operation(summary = "Update a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Character successfully updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Character not found with the given id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/characters/1
    public ResponseEntity<MovieCharacter> update(@RequestBody CharacterUpdateDTO characterDto, @PathVariable int id) {
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
     * @param id, the id corresponding to the character to be removed in the DB.
     * @return A response entity, encapsulating the success of the removal.
     */
    @Operation(summary = "Delete a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Character successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Malformed request"),
            @ApiResponse(responseCode = "404", description = "Character not found with the given Id")
    })
    @OnDelete(action= OnDeleteAction.CASCADE)
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/characters/1
   public ResponseEntity<MovieCharacter> delete(@PathVariable int id) {
        MovieCharacter character = characterService.findById(id);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        for (Movie movie : character.getMovies()) {
            movie.getCharacters().remove(character);
        }

        characterService.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}

