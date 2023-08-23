package a3.springweb.springweb.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.entities.Franchise;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;

@Service
public interface FranchiseService extends CrudService<Franchise, Integer>  {
    
    Franchise updateMovies(int[] movieIds, int franchiseId); 

    Collection<MovieCharacter> displayCharacters(int franchiseId);
    Collection<Movie> displayMovies(int franchiseId);
}
