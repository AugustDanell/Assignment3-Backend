package a3.springweb.springweb.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.Franchise;
import a3.springweb.springweb.model.Movie;
import a3.springweb.springweb.model.MovieCharacter;
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

    @Override
    public Franchise findById(Integer id){
        return franchiseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("error on id: " + id));
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        if (!franchiseRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        franchiseRepository.deleteById(id);
    }

    @Override
    public Franchise updateMovies(int[] movieIds, int franchiseId) {
       // return movieRepository.save(entity);
       Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new IllegalArgumentException(null, null));
       List<Movie> allMovies = movieRepository.findAll();
       for(Movie movie : allMovies){
        if(movie.getFranchise() != null && movie.getFranchise().getId() == franchiseId){
            movie.setFranchise(null);
        }
       }
       
       Set<Movie> movies = new HashSet<>();
       for(int id : movieIds){
        Movie movie = movieRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(null, null));
        movies.add(movie);
        //System.out.println("Characters: "+characters.size());
        //System.out.println("Character"+character);
       }

       //franchise.setMovies(movies);
       movies.forEach(m->{
        m.setFranchise(franchise);
       });

       return franchiseRepository.save(franchise);
    }

}
