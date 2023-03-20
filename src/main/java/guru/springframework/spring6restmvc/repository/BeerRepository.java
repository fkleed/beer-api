package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerRepository<T> {

    void store(Beer beer);
    List<Beer> findAll();
    Beer findById(UUID id);
    void deleteById(UUID id);
}
