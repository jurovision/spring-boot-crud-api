package uk.co.ryanharrison.crudapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.ryanharrison.crudapi.model.Coins;
import uk.co.ryanharrison.crudapi.repository.CoinsRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoinsService {

    private final CoinsRepository coinsRepository;

    @Transactional(readOnly = true)
    public Optional<Coins> getCoins(UUID id) {
        return coinsRepository.findById(id);
    }

    @Transactional
    public Coins saveCoins(Coins coins) {
        return coinsRepository.save(coins);
    }

}
