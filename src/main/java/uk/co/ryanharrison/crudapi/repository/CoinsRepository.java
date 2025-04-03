package uk.co.ryanharrison.crudapi.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.ryanharrison.crudapi.model.Coins;

import java.util.UUID;

@Repository
public interface CoinsRepository extends ListCrudRepository<Coins, UUID>, JpaSpecificationExecutor<Coins> {

}
