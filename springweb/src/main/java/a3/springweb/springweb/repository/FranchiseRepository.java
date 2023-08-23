package a3.springweb.springweb.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import a3.springweb.springweb.model.entities.Franchise;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
    
    //@Query("SELECT * FROM franchise WHERE id = 1")
    //Set<Franchise> findById(Integer Id);
}
