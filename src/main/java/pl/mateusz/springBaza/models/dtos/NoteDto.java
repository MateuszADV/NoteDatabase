package pl.mateusz.springBaza.models.dtos;

import lombok.AllArgsConstructor;
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

    public NoteDto(String numberCatalog, String country, String cod, String currency, double denomination, String dataNote, double priceBuy, double priceSell, int quantity, String quality, String notes) {
        this.numberCatalog = numberCatalog;
        this.country = country;
        this.cod = cod;
        this.currency = currency;
        this.denomination = denomination;
        this.dataNote = dataNote;
        this.priceBuy = priceBuy;
        this.priceSell = priceSell;
        this.quantity = quantity;
        this.quality = quality;
        this.notes = notes;
    }


}

