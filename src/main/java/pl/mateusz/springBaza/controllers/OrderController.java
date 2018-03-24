package pl.mateusz.springBaza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateusz.springBaza.models.CustomerModel;
import pl.mateusz.springBaza.models.ItemNoteModel;
import pl.mateusz.springBaza.models.NoteModel;
import pl.mateusz.springBaza.models.OrderModel;
import pl.mateusz.springBaza.models.repositories.CustomerRepository;
import pl.mateusz.springBaza.models.repositories.ItemNoteRepository;
import pl.mateusz.springBaza.models.repositories.NoteRepository;
import pl.mateusz.springBaza.models.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    OrderRepository orderRepository;
    ItemNoteRepository itemNoteRepository;
    CustomerRepository customerRepository;
    NoteRepository noteRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, ItemNoteRepository itemNoteRepository, CustomerRepository customerRepository, NoteRepository noteRepository) {
        this.orderRepository = orderRepository;
        this.itemNoteRepository = itemNoteRepository;
        this.customerRepository = customerRepository;
        this.noteRepository = noteRepository;
    }

    @GetMapping("/order")
    public String getOrder(ModelMap modelMap){

        List<OrderModel> orderModel = orderRepository.findAll();
        List<ItemNoteModel> itemNoteModel = itemNoteRepository.findAllByOrderModelId(100);

        modelMap.addAttribute("orders",orderModel);

        modelMap.addAttribute("itemOrder", itemNoteModel);

        return "order";
    }

    private String customerID = "";

    private OrderModel orderModel = new OrderModel();
    @PostMapping("/order")
    public String PostOrder(@RequestParam String customerId, ModelMap modelMap){

        if(customerId!="") {

            Optional<CustomerModel> customerModel = customerRepository.findById(Integer.valueOf(customerId));

            orderModel.setCustomerModel(customerModel.get());

            orderRepository.save(orderModel);

            customerID = customerId;
            modelMap.addAttribute("order", orderModel);
            return "redirect:/addorder";
        }
        return "redirect:/order";
    }

    @GetMapping("/addorder")
    public String getAddOrder(ModelMap modelMap){

        if(customerID!="") {
            //Optional<OrderModel> orderModel = orderRepository.findById(Long.valueOf(customerID));

            modelMap.addAttribute("order", orderModel);
            return "addorder";
        }

        return "redirect:/order";

    }


    private List<ItemNoteModel> itemNoteModels = new ArrayList<>();
    private Double suma=0.0;
    @PostMapping("/addorder")
    public String postAddOrder(@RequestParam String noteId, ModelMap modelMap){


        ItemNoteModel itemNoteModel = new ItemNoteModel();

        if(customerID!="") {
            modelMap.addAttribute("order", orderModel);
            Optional<NoteModel> noteModel = noteRepository.findById(Integer.valueOf(noteId));
            itemNoteModel.setNoteModel(noteModel.get());
            itemNoteModels.add(itemNoteModel);

            suma+=noteModel.get().getPriceSell();
            modelMap.addAttribute("suma",suma);
            modelMap.addAttribute("noteList",itemNoteModels);

            return "addorder";
        }

        return "addorder";
    }
}
