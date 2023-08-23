package a3.springweb.springweb.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.Franchise;
import a3.springweb.springweb.model.Movie;
import a3.springweb.springweb.model.MovieCharacter;
import a3.springweb.springweb.repository.CharacterRepository;
import a3.springweb.springweb.repository.MovieRepository;
import a3.springweb.springweb.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CharacterRepository characterRepository){
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    /** 
     * findById
     * A simple getter that returns the movie corrosponding to an id.
     * @param id, The ID corrosponding to a movie that is to be returned
     * @return The Movie corrosponding to the ID. 
     */

    @Override
    public Movie findById(Integer id){
        return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("error on id: " + id));
    }

    /**
     * findAll
     * A getter that returns every movie currently in the database.
     * @return A collection where every movie is contained (Collections can be Sets, Lists, etc).
     */

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * add
     * A function that takes in a movie and adds it to the repository.
     */

    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    /**
     * update
     * Updates the content of a movie object.
     */

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    /**
     * deleteById
     * Takes in an id and deletes the item with that id.
     * @param id, the id corrosponding to the movie object.
     */

    @Override
    public void deleteById(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        movieRepository.deleteById(id);
    }

    /**
     * updateCharacters
     * This function takes in an array of characters IDs and a movie ID.
     * For the movie corrosponding to the movie id, its characters are updated with the IDs inserted into this function.
     * @param characterIds The ids corrosponding to the updated characters in the movie.
     * @param movieId The id corrosponding to the movie to be updated.
     */

    @Override
    public Movie updateCharacters(int[] characterIds, int movieId) {
       // return movieRepository.save(entity);
       Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException(null, null));

       Set<MovieCharacter> characters = new HashSet<>();
       for(int id : characterIds){
        MovieCharacter character = characterRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(null, null));
        characters.add(character);
       }

       movie.setCharacters(characters);
       return movieRepository.save(movie);
    }
}
