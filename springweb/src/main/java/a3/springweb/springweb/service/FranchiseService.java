package a3.springweb.springweb.service;

import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.Franchise;
import a3.springweb.springweb.model.Movie;

@Service
public interface FranchiseService extends CrudService<Franchise, Integer>  {
    
    Franchise updateMovies(int[] movieIds, int franchiseId); 
}
