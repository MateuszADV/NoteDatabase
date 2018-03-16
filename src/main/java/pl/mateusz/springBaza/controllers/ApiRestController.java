package pl.mateusz.springBaza.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mateusz.springBaza.models.CustomerModel;
import pl.mateusz.springBaza.models.ItemNoteModel;
import pl.mateusz.springBaza.models.OrderModel;
import pl.mateusz.springBaza.models.dtos.OrderModelDto;
import pl.mateusz.springBaza.models.repositories.CustomerRepository;
import pl.mateusz.springBaza.models.repositories.ItemNoteRepository;
import pl.mateusz.springBaza.models.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiRestController {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ItemNoteRepository itemNoteRepository;

    @Autowired
    public ApiRestController(OrderRepository orderRepository, CustomerRepository customerRepository, ItemNoteRepository itemNoteRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemNoteRepository = itemNoteRepository;
    }

    @PostMapping("/api/order/")
    public ResponseEntity<OrderModelDto> PostOrder(){
        OrderModel orderModel = new OrderModel();


        ItemNoteModel itemNoteModel = new ItemNoteModel();
        itemNoteModel.setCod("PLN");
        itemNoteModel.setCountry("Polska");
        itemNoteModel.setCurency("10 zlotych");
        itemNoteModel.setDescryption("Polski banknot");
        itemNoteModel.setQuality("UNC");
        itemNoteModel.setQuantity(2);
        itemNoteModel.setPriceSell(25.0);
        itemNoteModel.setOrderModel(orderModel);

        itemNoteRepository.save(itemNoteModel);
        List<ItemNoteModel>itemNoteModelsList = new ArrayList<>();
        itemNoteModelsList.add(itemNoteModel);

      // orderModel.addItemOrder(itemNoteModel);


        Optional<CustomerModel> customerModel = customerRepository.findById(25);
        CustomerModel customerModel1 = customerModel.get();



        orderModel.setCustomerModel(customerModel1);
        orderModel.setItemOrder(itemNoteModelsList);


        orderRepository.save(orderModel);

        return ResponseEntity.ok().body((new ModelMapper().map(orderModel, OrderModelDto.class)));
    }
}
