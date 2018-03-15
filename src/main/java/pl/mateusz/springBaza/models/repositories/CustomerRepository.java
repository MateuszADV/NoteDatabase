package pl.mateusz.springBaza.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mateusz.springBaza.models.CustomerModel;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, Integer> {
    List<CustomerModel> findAllBy();
}
