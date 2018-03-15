package pl.mateusz.springBaza.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mateusz.springBaza.models.forms.NoteForm;

import javax.persistence.*;

@Entity
@Data
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

    @Override
    public int compareTo(NoteModel o) {
        return getCountry().compareTo(o.country);
    }
}
