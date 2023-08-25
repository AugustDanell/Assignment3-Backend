package a3.springweb.springweb.service.character;

import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.service.CrudService;

@Service
public interface CharacterService extends CrudService<MovieCharacter, Integer>  {
    
}