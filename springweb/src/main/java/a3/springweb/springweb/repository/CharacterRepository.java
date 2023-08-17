package a3.springweb.springweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import a3.springweb.springweb.model.Franchise;

@Repository
public interface CharacterRepository extends JpaRepository<Franchise, Integer> {
    
}
