package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.UUID;

public interface BeerRepository<T> {

    BeerDTO store(BeerDTO beer);

    List<BeerDTO> findAll();

    BeerDTO findById(UUID id);

    void deleteById(UUID id);

    void updateBeerById(UUID beerId, BeerDTO beer);

    void patchBeerById(UUID beerId, BeerDTO beer);
}
