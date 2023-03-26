package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.repository.BeerRepositoryOld;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepositoryOld beerRepositoryOld;

    @Override
    public List<BeerDTO> listBeers() {

        log.debug("Get all beer in service");

        return beerRepositoryOld.findAll();
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return Optional.ofNullable(beerRepositoryOld.findById(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {

        log.debug("Save new beer to repository: " + beer.getBeerName());

        return beerRepositoryOld.store(beer);
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {

        log.debug("Update existing beer with Id: " + beerId);

        return Optional.of(beerRepositoryOld.updateBeerById(beerId, beer));
    }

    @Override
    public Boolean deleteById(UUID beerId) {

        log.debug("Delete existing beer with Id: " + beerId);

        return beerRepositoryOld.deleteById(beerId);
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {

        log.debug("Patch existing beer with Id: " + beerId);

        return Optional.of(beerRepositoryOld.patchBeerById(beerId, beer));
    }


}
