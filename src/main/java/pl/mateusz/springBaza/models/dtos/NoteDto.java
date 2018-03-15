package pl.mateusz.springBaza.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDto {

    private Integer id;
    private String numberCatalog;
    private String country;
    private String cod;
    private String currency;
    private double denomination;
    private String dataNote;
    private double priceBuy;
    private double priceSell;
    private int quantity;
    private String quality;
    private String notes;
}
