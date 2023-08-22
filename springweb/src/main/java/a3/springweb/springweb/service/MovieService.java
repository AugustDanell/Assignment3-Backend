package a3.springweb.springweb.service;

import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.Movie;

@Service
public interface MovieService extends CrudService<Movie, Integer> {

    Movie updateCharacters(int[] charIds, int id);
}
