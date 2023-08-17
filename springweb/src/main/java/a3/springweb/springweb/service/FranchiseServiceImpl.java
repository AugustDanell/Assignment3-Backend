package a3.springweb.springweb.service;

import org.springframework.beans.factory.annotation.Autowired;

import a3.springweb.springweb.repository.FranchiseRepository;

public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Autowired
    public FranchiseServiceImpl(FranchiseRepository franchiseRepository){
        this.franchiseRepository= franchiseRepository;
    }

}
