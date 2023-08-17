package a3.springweb.springweb.service;

import org.springframework.beans.factory.annotation.Autowired;

import a3.springweb.springweb.repository.MovieRepository;

public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

}
