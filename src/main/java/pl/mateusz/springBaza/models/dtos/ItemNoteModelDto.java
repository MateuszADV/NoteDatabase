package pl.mateusz.springBaza.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mateusz.springBaza.models.OrderModel;

@Getter
@Setter
@NoArgsConstructor
public class ItemNoteModelDto {

    private Long id;

    private String country;
    private String cod;
    private String curency;
    private String quality;
    private int quantity;
    private String descryption;
    private Double priceSell;
    private OrderModel orderModel;
}
