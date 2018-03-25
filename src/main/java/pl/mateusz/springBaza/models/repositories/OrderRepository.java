package pl.mateusz.springBaza.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.springBaza.models.OrderModel;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    List<OrderModel> findByCustomerModel_Id(Integer customerId);

}
