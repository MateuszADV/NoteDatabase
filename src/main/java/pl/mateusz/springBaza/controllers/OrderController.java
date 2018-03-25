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
        List<ItemNoteModel> itemNoteModel = itemNoteRepository.findAllByOrderModelId(173);

        modelMap.addAttribute("orders",orderModel);

        modelMap.addAttribute("itemOrder", itemNoteModel);

        return "order";
    }

    private String customerID = "";
    private List<ItemNoteModel> itemNoteModels = new ArrayList<>();
    private OrderModel orderModel;

    @PostMapping("/order")
    public String PostOrder(@RequestParam String customerId, ModelMap modelMap){

        orderModel = new OrderModel();
        itemNoteModels.clear();
        if(customerId!="") {

            Optional<CustomerModel> customerModel = customerRepository.findById(Integer.valueOf(customerId));

            if(customerModel.isPresent()) {
                orderModel.setCustomerModel(customerModel.get());
                orderRepository.save(orderModel);

                customerID = customerId;
                modelMap.addAttribute("order", orderModel);
                return "redirect:/addorder";
            }else {
                return "redirect:/customer";
            }
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



    private Double suma=0.0;
    @PostMapping("/addorder")
    public String postAddOrder(@RequestParam String noteId, ModelMap modelMap){


        ItemNoteModel itemNoteModel = new ItemNoteModel();

        if(customerID!="") {
            modelMap.addAttribute("order", orderModel);
            Optional<NoteModel> noteModel = noteRepository.findById(Integer.valueOf(noteId));

            Optional<OrderModel> orderModel2 = orderRepository.findById(orderModel.getId());

            itemNoteModel.setOrderModel(orderModel2.get());
            itemNoteModel.setNoteModel(noteModel.get());
            itemNoteModel.setPriceSell(noteModel.get().getPriceSell());
            itemNoteModels.add(itemNoteModel);
            itemNoteRepository.save(itemNoteModel);

            suma+=noteModel.get().getPriceSell();
            modelMap.addAttribute("suma",suma);
            modelMap.addAttribute("noteList",itemNoteModels);

            return "addorder";
        }

        return "addorder";
    }

    @GetMapping("/ordercustomer")
    public String getOrderCustomer(ModelMap modelMap){

        modelMap.addAttribute("pusty","pusty");

        return "ordercustomer";
    }

    @PostMapping("/ordercustomer")
    public String postOrderCustomer(@RequestParam String customerId, ModelMap modelMap){



        List<OrderModel> orderModelList = orderRepository.findByCustomerModel_Id(Integer.valueOf(customerId));

        if(!orderModelList.isEmpty()) {
            modelMap.addAttribute("orders", orderModelList);
        }else{
            modelMap.addAttribute("customer", "Bark zamówień dla danego klienta, lib podane ID jest niepoprawne");
            modelMap.addAttribute("pusty","pusty");
        }
        return "ordercustomer";
    }

}
