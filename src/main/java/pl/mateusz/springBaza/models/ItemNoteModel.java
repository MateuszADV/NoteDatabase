package pl.mateusz.springBaza.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "item_note")
public class ItemNoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;
    private String cod;
    private String curency;
    private String quality;
    private int quantity;
    private String descryption;
    private Double priceSell;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel orderModel;

}
