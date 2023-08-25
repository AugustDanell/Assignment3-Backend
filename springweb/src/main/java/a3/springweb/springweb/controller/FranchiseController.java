package a3.springweb.springweb.controller;

import java.net.URI;
import java.util.Collection;

// Spring and Hibernate imports:
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
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
import a3.springweb.springweb.mappers.FranchiseMapper;
import a3.springweb.springweb.mappers.MovieMapper;
import a3.springweb.springweb.model.dtos.character.CharacterDTO;
import a3.springweb.springweb.model.dtos.franchise.FranchiseDTO;
import a3.springweb.springweb.model.dtos.franchise.FranchisePostDTO;
import a3.springweb.springweb.model.dtos.franchise.FranchiseUpdateDTO;
import a3.springweb.springweb.model.dtos.movie.MovieDTO;
import a3.springweb.springweb.model.entities.Franchise;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.repository.FranchiseRepository;
import a3.springweb.springweb.service.franchise.FranchiseService;

// Swagger imports (For API documentation):
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/v1/franchises")
public class FranchiseController {

        private final FranchiseService franchiseService;
        private final FranchiseMapper franchiseMapper;
        private final CharacterMapper characterMapper;
        private final MovieMapper movieMapper;

        @Autowired
        public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper,
                        CharacterMapper characterMapper, MovieMapper movieMapper) {
                this.franchiseService = franchiseService;
                this.franchiseMapper = franchiseMapper;
                this.characterMapper = characterMapper;
                this.movieMapper = movieMapper;
        }

        /**
         * getAll()
         * Maps to a GET request.
         * Returns all franchises in the data base.
         * 
         * @return A response entity with the collection of every franchise in the
         *         database.
         */

        @Operation(summary = "Get all franchises")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
                        }),
                        @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
        })
        @GetMapping // GET: localhost:8080/api/v1/franchises
        public ResponseEntity<Collection<FranchiseDTO>> getAll() {
                return ResponseEntity.ok(
                                franchiseMapper.franchiseToFranchiseDto(
                                                franchiseService.findAll()));
        }

        /**
         * getById()
         * Maps to a GET request.
         * Takes in an ID and returns a FranchiseDTO corresponding to that id.
         * 
         * @param id
         * @return The response entity with a franchiseDTO, corresponding to id.
         */

        @Operation(summary = "Get a franchise by id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = FranchiseDTO.class)) }),
                        @ApiResponse(responseCode = "404", description = "Franchise does not exist with the given ID", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
        })
        @GetMapping("{id}") // GET: localhost:8080/api/v1/franchises/1
        public ResponseEntity<FranchiseDTO> getById(@PathVariable int id) {
                return ResponseEntity.ok(
                                franchiseMapper.franchiseToFranchiseDto(
                                                franchiseService.findById(id)));
        }

        /**
         * add()
         * Maps to a POST request.
         * Adds a Franchise to the database.
         * 
         * @param franchiseDto, A franchiseDTO that is inserted in the body of the
         *                      request.
         * @return A response entity, corresponding to the success of the creation of a
         *         franchise.
         */

        @Operation(summary = "Add a franchise")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Franchise created", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = FranchiseDTO.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
        })
        @PostMapping // POST: localhost:8080/api/v1/franchises
        public ResponseEntity<FranchisePostDTO> add(@RequestBody FranchisePostDTO franchiseDto) {
                Franchise fran = franchiseService.add(
                                franchiseMapper.franchisePostDtoToFranchise(franchiseDto));
                URI location = URI.create("franchises/" + fran.getId());
                return ResponseEntity.created(location).build();
        }

        /**
         * update()
         * Maps to a PUT request.
         * Updates a franchise in the database.
         * 
         * @param franchiseDto, A franchiseDTO that corresponds to a character that is
         *                      to be updated.
         * @param id,           The id of the character object to be updated.
         * @return The success of the updated.
         */

        @Operation(summary = "Update a franchise")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Franchise successfully updated", content = @Content),
                        @ApiResponse(responseCode = "400", description = "Malformed request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Franchise not found with the given id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
        })
        @PutMapping("{id}") // PUT: localhost:8080/api/v1/franchises/1
        public ResponseEntity<FranchiseUpdateDTO> update(@RequestBody FranchiseUpdateDTO franchiseDto,
                        @PathVariable int id) {
                // Validates if body is correct
                if (id != franchiseDto.getId())
                        return ResponseEntity.badRequest().build();
                franchiseService.update(
                                franchiseMapper.franchiseUpdateDtoToFranchise(franchiseDto));
                return ResponseEntity.noContent().build();
        }

        /**
         * delete()
         * Maps to a DELETE request.
         * Takes in an id via the URL path and removes the corresponding franchise from
         * the DB. Movies in said franchise are set to have their franchise as null.
         * 
         * @param id, The id of the franchise to be removed.
         * @return A Response Entity, telling us the success of the operation.
         */

        @Operation(summary = "Delete a franchise")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Franchise successfully deleted"),
                        @ApiResponse(responseCode = "400", description = "Malformed request"),
                        @ApiResponse(responseCode = "404", description = "Franchise not found with the given Id")
        })
        @OnDelete(action = OnDeleteAction.CASCADE)
        @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/franchises/1
        public ResponseEntity<Franchise> delete(@PathVariable int id) {
                Franchise franchise = franchiseService.findById(id);
                for(Movie movie : franchise.getMovies()){
                        movie.setFranchise(null);
                }
                
                franchiseService.deleteById(id);
                return ResponseEntity.noContent().build();
        }

        /**
         * updateMovies()
         * Maps to a PUT request.
         * Take in a id via the URL path and an array of integers (movie ids) provided
         * via the body of the request.
         * For every movie id, that movie is inserted into the franchise.
         * 
         * @param movieIds, The int[] array of movie ids.
         * @param id,       The ID corresponding to a franchise in the DB.
         * @return A Response Entity, telling us the success of the operation.
         */

        @Operation(summary = "Update movies in a franchise")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Movies successfully updated", content = @Content),
                        @ApiResponse(responseCode = "400", description = "Malformed request", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
                        @ApiResponse(responseCode = "404", description = "Franchise not found with the given ID", content = @Content)
        })
        @PutMapping("movies/{id}")
        public ResponseEntity<FranchiseDTO> updateMovies(@RequestBody int[] movieIds, @PathVariable int id) {
                franchiseService.updateMovies(movieIds, id);
                return ResponseEntity.noContent().build();
        }

        /**
         * getCharactersInFranchise()
         * Maps to a GET request.
         * Takes in an id in the path corresponding to the id of a franchise.
         * For this franchise, every character is returned, for every movie in the
         * franchise.
         * 
         * @param id, The franchise ID corresponding to the wanted franchise
         * @return A response entity, telling us if the operation was successful.
         */

        @Operation(summary = "Get all characters in a franchise")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class))) }),
                        @ApiResponse(responseCode = "404", description = "Franchise does not exist with the given ID", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) })
        })
        @GetMapping("characters/{id}") // PUT: localhost:8080/api/v1/franchises/characters/1
        public ResponseEntity<Collection<CharacterDTO>> getCharactersInFranchise(@PathVariable int id) {
                return ResponseEntity.ok(
                                characterMapper.characterToCharacterDto(
                                                franchiseService.displayCharacters(id)));
        }

        /**
         * getMoviesInFranchise
         * Maps to a GET request.
         * Takes in a franchise ID via the URL pathing. This ID is then subsequently
         * used to get every movie from the DB
         * belonging to a franchise corresponding to the inserted ID.
         * 
         * @param id, The id corresponding to the wanted franchise.
         * @return A Response Entity telling us the success of the operation.
         */
        @Operation(summary = "Get all movies in a franchise")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class))) }),
                        @ApiResponse(responseCode = "404", description = "Franchise does not exist with the given ID", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) })
        })
        @GetMapping("movies/{id}") // GET: localhost:8080/api/v1/franchises/movies/1
        public ResponseEntity<Collection<MovieDTO>> getMoviesInFranchise(@PathVariable int id) {
                return ResponseEntity.ok(
                                movieMapper.movieToMovieDto(
                                                franchiseService.displayMovies(id)));
        }
}
