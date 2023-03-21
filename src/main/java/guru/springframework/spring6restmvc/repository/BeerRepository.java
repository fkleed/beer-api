package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerRepository<T> {

    Beer store(Beer beer);

    List<Beer> findAll();

    Beer findById(UUID id);

    void deleteById(UUID id);

    void updateBeerById(UUID beerId, Beer beer);

    void patchBeerById(UUID beerId, Beer beer);
}
