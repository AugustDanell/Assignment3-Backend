package a3.springweb.springweb.service.character;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.exception.CharacterNotFoundException;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.repository.CharacterRepository;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;

    }

    /**
     * findById()
     * Returns a MovieCharacter object corresponding to a provided ID.
     * 
     * @param id, A character id
     * @return The character with the corresponding id in the DB, if it exists.
     */

    @Override
    public MovieCharacter findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
    }

    /**
     * findAll()
     * Returns a collection of every MovieCharacter found in the database.
     * 
     * @return Every character in the DB.
     */

    @Override
    public Collection<MovieCharacter> findAll() {
        return characterRepository.findAll();
    }

    /**
     * add()
     * Adds a MovieCharacter entity to the database.
     * 
     * @param entity, The entity object that is added.
     * @return
     */

    @Override
    public MovieCharacter add(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    /**
     * update()
     * Takes in a MovieCharacter entity and updates that entity in the database.
     * 
     * @param entity, The entity that is updated.
     */

    @Override
    public MovieCharacter update(MovieCharacter entity) {
        return characterRepository.save(entity);
    }

    /**
     * deleteById()
     * Deletes a character in the database on the inserted ID.
     * 
     * @param id, The id of the character to be deleted.
     */

    @Override
    public void deleteById(Integer id) {
        if (!characterRepository.existsById(id)) {
            throw new CharacterNotFoundException(id);
        }
        characterRepository.deleteById(id);
    }
}
