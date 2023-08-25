package a3.springweb.springweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import a3.springweb.springweb.model.entities.Franchise;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {
}
