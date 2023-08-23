package a3.springweb.springweb.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.entities.Franchise;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
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
     * Returns a MovieCharacter object corresponding to a provided ID.
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
     * Adds a MovieCharacter entity to the database.
     * @param entity, The entity object that is added.
     */

    @Override
    public MovieCharacter add(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    /**
     * update()
     * Takes in a MovieCharacter entity and updates that entity in the database.
     * @param entity, The entity that is updated.
     */

    @Override
    public MovieCharacter update(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    /**
     * deleteById()
     * Deletes a character in the database on the inserted ID.
     * @param id, The id of the character to be deleted.
     */

    @Override
    public void deleteById(Integer id) {
        if (!characterRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        characterRepository.deleteById(id);
    }
}
