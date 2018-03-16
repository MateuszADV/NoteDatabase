package pl.mateusz.springBaza.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.springBaza.models.ItemNoteModel;

public interface ItemNoteRepository extends JpaRepository<ItemNoteModel, Long> {

}
