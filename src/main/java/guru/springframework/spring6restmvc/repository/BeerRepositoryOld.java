package guru.springframework.spring6restmvc.repository;

import guru.springframework.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.UUID;

public interface BeerRepositoryOld<T> {

    BeerDTO store(BeerDTO beer);

    List<BeerDTO> findAll();

    BeerDTO findById(UUID id);

    BeerDTO updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteById(UUID id);

    BeerDTO patchBeerById(UUID beerId, BeerDTO beer);
}
