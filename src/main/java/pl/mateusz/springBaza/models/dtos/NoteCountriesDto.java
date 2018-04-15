package pl.mateusz.springBaza.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteCountriesDto {
    private String country;

    public NoteCountriesDto(String country) {
        this.country = country;
    }
}
