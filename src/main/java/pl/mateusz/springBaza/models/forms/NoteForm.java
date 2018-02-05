package pl.mateusz.springBaza.models.forms;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class NoteForm {
    private String numberCatalog;
    @NotEmpty(message = "pole nie może być puste")
    private String country;
    @NotEmpty(message = "pole nie może być puste")
    private String cod;
    @NotEmpty(message = "pole nie może być puste")
    private String currency;
    @NotNull(message = "pole nie może być puste")
    private double denomination;  //double
    private String dataNote;
    @NotNull(message = "pole nie może być puste")
    private double priceBuy;    //double
    private double priceSell;   //double
    private int quantity;    //int
    @NotEmpty(message = "pole nie może być puste")
    private String quality;
    private String notes;
    @NotEmpty(message = "pole nie może być puste")
    private String pathNoteSizeA;
    @NotEmpty(message = "pole nie może być puste")
    private String pathNoteSizeB;


}
