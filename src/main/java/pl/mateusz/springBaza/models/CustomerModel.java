package pl.mateusz.springBaza.models;

import lombok.*;
import pl.mateusz.springBaza.models.forms.CustomerForm;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "customers")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String numberCustomer;
    private String name;
    private String lastname;
    private String zipCode;
    private String city;
    private String street;
    private String numberStreet;
    private String nick;
    private String phone;
    private String email;
    private String comments;

    public CustomerModel(CustomerForm form){
        numberCustomer=form.getNumberCustomer();
        name=form.getName();
        lastname=form.getLastname();
        zipCode=form.getZipCode();
        city=form.getCity();
        street=form.getStreet();
        numberStreet=form.getNumberStreet();
        nick=form.getNick();
        phone=form.getPhone();
        email=form.getEmail();
        comments=form.getComments();
    }
}
