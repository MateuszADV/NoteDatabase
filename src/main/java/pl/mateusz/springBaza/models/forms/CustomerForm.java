package pl.mateusz.springBaza.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class CustomerForm {
    private String numberCustomer;
    private String name;
    private String lastname;
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String zipCode;
    private String city;
    private String street;
    private String numberStreet;
    private String nick;
    private String phone;
    private String email;
    private String comments;
}
