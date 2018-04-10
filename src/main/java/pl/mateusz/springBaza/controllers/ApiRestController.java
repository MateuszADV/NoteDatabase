package pl.mateusz.springBaza.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mateusz.springBaza.models.CustomerModel;
import pl.mateusz.springBaza.models.ItemNoteModel;
import pl.mateusz.springBaza.models.NoteModel;
import pl.mateusz.springBaza.models.OrderModel;
import pl.mateusz.springBaza.models.dtos.ItemNoteModelDto;
import pl.mateusz.springBaza.models.dtos.OrderModel2Dto;
import pl.mateusz.springBaza.models.dtos.OrderModelDto;
import pl.mateusz.springBaza.models.repositories.CustomerRepository;
import pl.mateusz.springBaza.models.repositories.ItemNoteRepository;
import pl.mateusz.springBaza.models.repositories.NoteRepository;
import pl.mateusz.springBaza.models.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiRestController {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ItemNoteRepository itemNoteRepository;
    private NoteRepository noteRepository;

    @Autowired
    public ApiRestController(OrderRepository orderRepository, CustomerRepository customerRepository, ItemNoteRepository itemNoteRepository, NoteRepository noteRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemNoteRepository = itemNoteRepository;
        this.noteRepository = noteRepository;
    }

    @PostMapping("/api/order/")
    public ResponseEntity<OrderModelDto> PostOrder(){
        OrderModel orderModel = new OrderModel();

        Optional<NoteModel> noteModel = noteRepository.findById(26);

        ItemNoteModel itemNoteModel = new ItemNoteModel();
        itemNoteModel.setNoteModel(noteModel.get());
        itemNoteModel.setPriceSell(25.0);
       // itemNoteModel.setOrderModel(orderModel);

        itemNoteRepository.save(itemNoteModel);

        List<ItemNoteModel>itemNoteModelsList = new ArrayList<>();
        itemNoteModelsList.add(itemNoteModel);

        Optional<CustomerModel> customerModel = customerRepository.findById(24);
        CustomerModel customerModel1 = customerModel.get();

        orderModel.setCustomerModel(customerModel1);
        orderModel.setItemOrder(itemNoteModelsList);
       // orderModel.setItemNoteModel(itemNoteModel);

        orderRepository.save(orderModel);

        return ResponseEntity.ok().body((new ModelMapper().map(orderModel, OrderModelDto.class)));
    }

    @PostMapping("/api/order/{orderId}/note/{noteId}")
    public ResponseEntity<OrderModelDto> addedNote(@PathVariable Long orderId,
                                                      @PathVariable Integer noteId){
        Optional<OrderModel> orderModel = orderRepository.findById(orderId);
        Optional<NoteModel> noteModel = noteRepository.findById(noteId);

        ItemNoteModel itemNoteModel = new ItemNoteModel();
        itemNoteModel.setOrderModel(orderModel.get());
        itemNoteModel.setNoteModel(noteModel.get());
        itemNoteModel.setPriceSell(1111.99);

        itemNoteRepository.save(itemNoteModel);

        OrderModelDto orderModelDto = new OrderModelDto();
        OrderModel orderModel1 = new OrderModel();
        orderModel1 = orderModel.get();

        return ResponseEntity.ok().body((new ModelMapper().map(orderModel, OrderModelDto.class)));
    }

    @GetMapping("api/order/{orderId}")
    public ResponseEntity<OrderModelDto> orderList(@PathVariable Long orderId){
        Optional<OrderModel> orderModel = orderRepository.findById(orderId);

        List<ItemNoteModel> itemNoteModel = itemNoteRepository.findAllByOrderModelId(orderId);
        List<ItemNoteModelDto> itemNoteModelDtos = new ArrayList<>();

        for (ItemNoteModel noteModel : itemNoteModel) {
            itemNoteModelDtos.add((new ModelMapper().map(itemNoteModel, ItemNoteModelDto.class)));
        }

        OrderModel orderModel1 = orderModel.get();

        OrderModelDto orderModelDto = new OrderModelDto();
        orderModelDto = (new ModelMapper().map(orderModel1, OrderModelDto.class));

        orderModelDto.setItemOrder(itemNoteModel);

        //orderModel1.setItemOrder(itemNoteModel);
       /* List<OrderModel> orderModelList = new ArrayList<>();

        for (OrderModel model : orderModel) {
            orderModelList.add(model);
        }*/


        return ResponseEntity.ok().body(orderModelDto);
    }

    @GetMapping("api/order2/{orderId}")
    public ResponseEntity<OrderModel2Dto> orderList2(@PathVariable Long orderId){
        Optional<OrderModel> orderModel = orderRepository.findById(orderId);

        List<ItemNoteModel> itemNoteModel = itemNoteRepository.findAll();
        List<ItemNoteModelDto> itemNoteModelDtos = new ArrayList<>();

        for (ItemNoteModel noteModel : itemNoteModel) {
            itemNoteModelDtos.add((new ModelMapper().map(itemNoteModel, ItemNoteModelDto.class)));
        }

        OrderModel orderModel2 = orderModel.get();

        OrderModel2Dto orderModel2Dto = new OrderModel2Dto();
        orderModel2Dto = (new ModelMapper().map(orderModel2, OrderModel2Dto.class));

        orderModel2Dto.setItemOrder(itemNoteModelDtos);

        return ResponseEntity.ok().body(orderModel2Dto);
    }

    @GetMapping("api/orderitem/")
    public ResponseEntity<List<ItemNoteModel>> orderList(){
        List<ItemNoteModel> itemNoteModel = itemNoteRepository.findAll();

        List<ItemNoteModelDto> itemNoteModelDtoList = new ArrayList<>();

        for (ItemNoteModel noteModel : itemNoteModel) {
            itemNoteModelDtoList.add((new ModelMapper().map(itemNoteModel, ItemNoteModelDto.class)));
        }

        return ResponseEntity.ok(itemNoteModel);
    }
}
