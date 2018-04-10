package pl.mateusz.springBaza.models;

import lombok.*;
import pl.mateusz.springBaza.models.forms.NoteForm;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "banknoty")

public class NoteModel implements Comparable<NoteModel>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String pathNoteSizeA;
    private String pathNoteSizeB;


    public NoteModel(NoteForm form){
        numberCatalog = form.getNumberCatalog();
        country = form.getCountry();
        cod = form.getCod();
        currency = form.getCurrency();
        denomination = form.getDenomination();
        dataNote = form.getDataNote();
        priceBuy = form.getPriceBuy();
        priceSell = form.getPriceSell();
        quantity = form.getQuantity();
        quality = form.getQuality();
        notes = form.getNotes();
        pathNoteSizeA = form.getPathNoteSizeA();
        pathNoteSizeB = form.getPathNoteSizeB();
    }

    public NoteModel(String numberCatalog, String country, String cod, String currency, double denomination, String dataNote, double priceBuy, double priceSell, int quantity, String quality, String notes) {
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

    @Override
    public int compareTo(NoteModel o) {
        return getCountry().compareTo(o.country);
    }
}
