package pl.mateusz.springBaza.models.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mateusz.springBaza.models.CustomerModel;
import pl.mateusz.springBaza.models.ItemNoteModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderModelDto {

    private Long id;

    private Date added = new Date();

    private CustomerModel customerModel;

    List<ItemNoteModel> itemOrder = new ArrayList<>();

}
