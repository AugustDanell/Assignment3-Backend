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

    /**
     * findById()
     * Returns a MovieCharacter object corrosponding to a provided ID.
     */

    @Override
    public MovieCharacter findById(Integer id){
        return characterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("error on id: " + id));
    }

    /**
     * findAll()
     * Returns a collection of every MovieCharacter found in the database.
     */

    @Override
    public Collection<MovieCharacter> findAll() {
        return characterRepository.findAll();
    }

    /**
     * add()
     * 
     */

    @Override
    public MovieCharacter add(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    @Override
    public MovieCharacter update(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        if (!characterRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        characterRepository.deleteById(id);
    }
}
