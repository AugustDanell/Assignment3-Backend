package a3.springweb.springweb.service;

import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.entities.MovieCharacter;

@Service
public interface CharacterService extends CrudService<MovieCharacter, Integer>  {
    
}