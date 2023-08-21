package a3.springweb.springweb.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import a3.springweb.springweb.model.Franchise;
import a3.springweb.springweb.model.Movie;
import a3.springweb.springweb.repository.FranchiseRepository;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository){
        this.franchiseRepository= franchiseRepository;
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

}
