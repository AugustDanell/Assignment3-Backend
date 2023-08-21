package a3.springweb.springweb.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.MovieCharacter;
import a3.springweb.springweb.model.Franchise;
import a3.springweb.springweb.model.Movie;
import a3.springweb.springweb.repository.CharacterRepository;

@Service
public class CharacterServiceImpl implements CharacterService{
      private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository){
        this.characterRepository= characterRepository;
    }

    @Override
    public MovieCharacter findById(Integer id){
        return characterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("error on id: " + id));
    }

    @Override
    public Collection<MovieCharacter> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public MovieCharacter add(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    @Override
    public MovieCharacter update(MovieCharacter entity) {
        return characterRepository.save(entity);
    }
}
