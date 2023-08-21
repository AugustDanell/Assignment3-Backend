package a3.springweb.springweb.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import a3.springweb.springweb.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    
}