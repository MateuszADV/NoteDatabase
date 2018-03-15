package pl.mateusz.springBaza.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mateusz.springBaza.models.NoteModel;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<NoteModel,Integer> {
    NoteModel findByNumberCatalog(String number);
    List<NoteModel> findByCountry(String country);
    List<NoteModel> findAllByCountryContains(String country);
    List<NoteModel> findAllBy();
    //List<NoteModel> countAllByCountry(String count);
    int countAllByCountry(String country);
    //List<NoteModel> countAllByCountryLimit1(String count); odczyt ostatniego elementu z bazy

}
