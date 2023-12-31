package a3.springweb.springweb.service.movie;

// Datastructs
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// SpringBoot and Hibernate imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.exception.CharacterNotFoundException;
import a3.springweb.springweb.exception.MovieNotFoundException;
// Internal imports:
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.repository.CharacterRepository;
import a3.springweb.springweb.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    /**
     * findById
     * A simple getter that returns the movie corresponding to an id.
     * 
     * @param id, The ID corresponding to a movie that is to be returned
     * @return The Movie corresponding to the ID.
     */

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    /**
     * findAll
     * A getter that returns every movie currently in the database.
     * 
     * @return A collection where every movie is contained (Collections can be Sets,
     *         Lists, etc).
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
    public Movie update(Movie newMovie) {
        Movie oldMovie = movieRepository.findById(newMovie.getId())
                .orElseThrow(() -> new MovieNotFoundException(newMovie.getId()));
        oldMovie.shallowCopyNotNull(newMovie);
        return movieRepository.save(oldMovie);
    }

    /**
     * deleteById
     * Takes in an id and deletes the item with that id.
     * 
     * @param id, the id corresponding to the movie object.
     */

    @Override
    public void deleteById(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    }

    /**
     * updateCharacters
     * This function takes in an array of characters IDs and a movie ID.
     * For the movie corresponding to the movie id, its characters are updated with
     * the IDs inserted into this function.
     * 
     * @param characterIds The ids corresponding to the updated characters in the
     *                     movie.
     * @param movieId      The id corresponding to the movie to be updated.
     */

    @Override
    public Movie updateCharacters(int[] characterIds, int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));

        Set<MovieCharacter> characters = new HashSet<>();
        for (int id : characterIds) {
            MovieCharacter character = characterRepository.findById(id)
                    .orElseThrow(() -> new CharacterNotFoundException(id));
            characters.add(character);
        }

        movie.setCharacters(characters);
        return movieRepository.save(movie);
    }

    /**
     * displayCharacters
     * Takes in an id of a movie and displaying its characters
     * 
     * @param movieDd, the id corresponding to the movie object.
     */

    @Override
    public Collection<MovieCharacter> displayCharacters(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        return movie.getCharacters();

    }
}
