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

    @Override
    public Movie findById(Integer id){
        return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("error on id: " + id));
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateCharacters(int[] characterIds, int movieId) {
       // return movieRepository.save(entity);
       Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException(null, null));

       Set<MovieCharacter> characters = new HashSet<>();
       for(int id : characterIds){
        MovieCharacter character = characterRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(null, null));
        characters.add(character);
        //System.out.println("Characters: "+characters.size());
        //System.out.println("Character"+character);
       }

       movie.setCharacters(characters);
       return movieRepository.save(movie);
    }
}
