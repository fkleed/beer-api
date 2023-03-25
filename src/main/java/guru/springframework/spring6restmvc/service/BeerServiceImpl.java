package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.repository.BeerRepository;
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

    private final BeerRepository beerRepository;

    @Override
    public List<BeerDTO> listBeers() {

        log.debug("Get all beer in service");

        return beerRepository.findAll();
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer by Id - in service. Id: " + id.toString());

        return Optional.ofNullable(beerRepository.findById(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {

        log.debug("Save new beer to repository: " + beer.getBeerName());

        return beerRepository.store(beer);
    }

    @Override
    public void updateBeerById(UUID beerId, BeerDTO beer) {

        log.debug("Update existing beer with Id: " + beerId);

        beerRepository.updateBeerById(beerId, beer);
    }

    @Override
    public void deleteById(UUID beerId) {

        log.debug("Delete existing beer with Id: " + beerId);

        beerRepository.deleteById(beerId);
    }

    @Override
    public void patchBeerById(UUID beerId, BeerDTO beer) {

        log.debug("Patch existing beer with Id: " + beerId);

        beerRepository.patchBeerById(beerId, beer);
    }


}
