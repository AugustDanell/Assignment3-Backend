package a3.springweb.springweb.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public interface CrudService<T, ID> {
    
    T findById(ID id);

    //Collection<T> findAll();

    //T add(T entity);

    //T update(T entity);

    //void deleteById(ID id);
 
}
