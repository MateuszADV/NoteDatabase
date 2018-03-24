package pl.mateusz.springBaza.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.springBaza.models.ItemNoteModel;

import java.util.List;
import java.util.Optional;

public interface ItemNoteRepository extends JpaRepository<ItemNoteModel, Long> {

    List<ItemNoteModel> findAllByOrderModelId(long orderId);

}

