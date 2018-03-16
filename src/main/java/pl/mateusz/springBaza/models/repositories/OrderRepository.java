package pl.mateusz.springBaza.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.springBaza.models.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

}
