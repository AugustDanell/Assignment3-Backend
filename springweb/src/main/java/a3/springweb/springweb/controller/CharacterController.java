package a3.springweb.springweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import a3.springweb.springweb.model.Character;
import a3.springweb.springweb.service.CharacterService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {
    
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    public ResponseEntity<Character> getById(@PathVariable int id) {
        return ResponseEntity.ok(characterService.findById(id));
    }
}

