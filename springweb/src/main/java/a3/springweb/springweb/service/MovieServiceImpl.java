package a3.springweb.springweb.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.Franchise;
import a3.springweb.springweb.model.Movie;
import a3.springweb.springweb.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
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
    public Movie updateCharacters(Movie entity) {
        return movieRepository.save(entity);
    }
}
