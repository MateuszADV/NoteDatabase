package pl.mateusz.springBaza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.springBaza.models.forms.CustomerForm;
import pl.mateusz.springBaza.models.CustomerModel;
import pl.mateusz.springBaza.models.repositories.CustomerRepository;
import pl.mateusz.springBaza.utils.UtilNote;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerControler {

    @Autowired
    CustomerRepository customerRepository;

    private List<CustomerModel> customerModelList = new ArrayList<>();
    private List<CustomerForm> customerList = new ArrayList<>();

    @PostMapping("/customer")
    public String customerPost(@ModelAttribute("customerForm") CustomerForm form, Model model){
        customerModelList = customerRepository.findAllBy(); //Zapisuje wszystkie rekordy z bazy danych o klientach do listy

        form.setNumberCustomer("K/"+UtilNote.nextData()+UtilNote.nextNumber(checkNumberCustomer(customerModelList))); //przypisywany jest kolejny numer klienta na podstawie ostatniego numeru z bazy
        customerRepository.save(new CustomerModel(form));  //Zapisywanie danych o kliencie do bazy danych
        customerList.add(form); //Dodawanie klienta do listy
        model.addAttribute("customerList",customerList); //Wysłanei listy klientów do wyswietlenie w tabei poniżej

        return "customer";
    }

    @GetMapping("/customer")
    public String custromerGet(Model model){
        model.addAttribute("customerForm",new CustomerForm());

        customerModelList = customerRepository.findAllBy();

        model.addAttribute("nextNumberCustomer", "K/"+UtilNote.nextData()+UtilNote.nextNumber(checkNumberCustomer(customerModelList)));


        return "customer";
    }

    //Metoda sprawdzająca czy lista klientów nie jest pusta jęśli jest pusta awraca "0"
    //jeśli nie to zwraca ostatni muner klienta
    private String checkNumberCustomer(List<CustomerModel> customerModelList) {
        if(customerModelList.isEmpty()){
            return "0";
        }else{
            return customerModelList.get(customerModelList.size()-1).getNumberCustomer();
        }
    }
}
