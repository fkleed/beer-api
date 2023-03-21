package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.repository.BeerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    @Override
    public List<Beer> listBeers() {

        log.debug("Get all beer in service");

        return beerRepository.findAll();
    }

    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return beerRepository.findById(id);
    }

    @Override
    public Beer saveNewBeer(Beer beer) {

        log.debug("Save new beer to repository: " + beer.getBeerName());

        return beerRepository.store(beer);
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {

        log.debug("Update existing beer with Id: " + beerId);

        beerRepository.updateBeerById(beerId, beer);
    }

    @Override
    public void deleteById(UUID beerId) {

        log.debug("Delete existing beer with Id: " + beerId);

        beerRepository.deleteById(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, Beer beer) {

        log.debug("Patch existing beer with Id: " + beerId);

        beerRepository.patchBeerById(beerId, beer);
    }


}
