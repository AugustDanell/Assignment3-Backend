package a3.springweb.springweb.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;

@Service
public interface MovieService extends CrudService<Movie, Integer> {

    Movie updateCharacters(int[] charIds, int id);
    
    Collection<MovieCharacter> displayCharacters(int movieId);

}
