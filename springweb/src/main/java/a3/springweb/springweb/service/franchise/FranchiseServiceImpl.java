package a3.springweb.springweb.service.franchise;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.exception.FranchiseNotFoundException;
import a3.springweb.springweb.exception.MovieNotFoundException;
import a3.springweb.springweb.model.entities.Franchise;
import a3.springweb.springweb.model.entities.Movie;
import a3.springweb.springweb.model.entities.MovieCharacter;
import a3.springweb.springweb.repository.FranchiseRepository;
import a3.springweb.springweb.repository.MovieRepository;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, MovieRepository movieRepository){
        this.franchiseRepository= franchiseRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * findById
     * Finds a Franchise object from the database corresponding to a provided id.
     * @param id, The provided id to match on.
     * @return A Franchise object with said id.
     */

    @Override
    public Franchise findById(Integer id){
        return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    /**
     * findAll
     * Returns a collection of franchises stored in the database.
     * @return A collection of franchises.
     */

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    /**
     * add
     * Adds a franchise entity to the database.
     */

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    /**
     * update
     * Takes in a Franchise entity and updates the database with it.
     * @param entity, The Franchise entity to be updated.
     */

    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    /**
     * deleteById
     * Deletes a franchise from the database by the id. 
     */

    @Override
    public void deleteById(Integer id) {
        if (!franchiseRepository.existsById(id)) {
            throw new FranchiseNotFoundException(id);
        }
        franchiseRepository.deleteById(id);
    }

    /**
     * updateMovies
     * A method that takes in an array of movie ids and a franchiseId.
     * The Franchise object with the corresponding franchiseId is extracted as franchise, and then we do two things with that:
     * 1. First we remove its previous references, every movie pointing to franchise has their franchise reference set to null.
     * 2. We iterate over every movie extracted from movieIds and let them point to franchise.
     * @param movieIds, An array of movie ids that are to be included in the franchise corresponding to the inserted id.
     * @param franchiseId, An id corresponding to the franchise we want to have the movies inserted into. 
     */

    @Override
    public Franchise updateMovies(int[] movieIds, int franchiseId) {
       Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
       List<Movie> allMovies = movieRepository.findAll();
       for(Movie movie : allMovies){
        if(movie.getFranchise() != null && movie.getFranchise().getId() == franchiseId){
            movie.setFranchise(null);
        }
       }
       
       Set<Movie> movies = new HashSet<>();
       for(int id : movieIds){
        Movie movie = movieRepository.findById(id)
        .orElseThrow(() -> new MovieNotFoundException(id));
        movies.add(movie);
       }

       movies.forEach(m->{
        m.setFranchise(franchise);
       });

       return franchiseRepository.save(franchise);
    }

    @Override
    public Collection<MovieCharacter> displayCharacters(int franchiseId){
        List <Movie> allMovies = movieRepository.findAll();
        HashSet <MovieCharacter> charactersInFranchise = new HashSet<>();
        for(Movie movie : allMovies){
            if(movie.getFranchise() != null && movie.getFranchise().getId() == franchiseId){
                for(MovieCharacter character : movie.getCharacters()){
                    charactersInFranchise.add(character);
                }
            }
        }

        return charactersInFranchise;
    }

    @Override
    public Collection<Movie> displayMovies(int franchiseId){
        Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
        return franchise.getMovies();

    }

}
