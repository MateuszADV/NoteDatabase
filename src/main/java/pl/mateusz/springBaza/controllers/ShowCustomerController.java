package pl.mateusz.springBaza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mateusz.springBaza.models.CustomerModel;
import pl.mateusz.springBaza.models.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ShowCustomerController {

    @Autowired
    CustomerRepository customerRepository;

    private List<CustomerModel> customerList = new ArrayList<>();

    @GetMapping("/showcustomer")
    public String showCustomerGet(Model model){
        customerList = customerRepository.findAllBy();
        model.addAttribute("customerList",customerList);
        return "showcustomer";
    }

}
