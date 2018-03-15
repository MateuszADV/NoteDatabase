package pl.mateusz.springBaza.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orderItem")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date added = new Date();

    @ManyToOne
    private CustomerModel customerModel;

    @OneToMany
    List<ItemNoteModel> itemOrder = new ArrayList<>();


    public void addItemOrder(ItemNoteModel itemNoteModel){
        itemOrder.add(itemNoteModel);
        itemNoteModel.setOrderModel(this);
    }
}
