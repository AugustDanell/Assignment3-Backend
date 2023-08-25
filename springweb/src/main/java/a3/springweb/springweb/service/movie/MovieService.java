package a3.springweb.springweb.service.movie;

import java.util.Collection;

import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.service.CrudService;

@Service
public interface MovieService extends CrudService<Movie, Integer> {

    Movie updateCharacters(int[] charIds, int id);

    Collection<MovieCharacter> displayCharacters(int movieId);

}
