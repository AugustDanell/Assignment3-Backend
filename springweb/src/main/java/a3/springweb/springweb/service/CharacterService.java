package a3.springweb.springweb.service;

import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.Character;

@Service
public interface CharacterService extends CrudService<Character, Integer>  {
    
}