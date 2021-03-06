package pl.mateusz.springBaza.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mateusz.springBaza.models.NoteModel;
import pl.mateusz.springBaza.models.dtos.NoteDto;
import pl.mateusz.springBaza.models.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class RestControler {

    @Autowired
    NoteRepository noteRepository;

    @RequestMapping(value = "/rest/shownote",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity banknoty(){
        return new ResponseEntity(noteRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/shownote/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity banknoty(@PathVariable("id") int someId){
        noteRepository.deleteById(someId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/shownote/{country}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity banknoty(@PathVariable("country") String someCountry, Model model){

        return new ResponseEntity(noteRepository.findByCountry(someCountry), HttpStatus.OK);
    }

    @GetMapping("/api/country")
    public ResponseEntity<List<NoteDto>> getNote(){
            //jak znienić to na listę
        //List<NoteModel> noteModelList = StreamSupport.stream(noteRepository.findAll().spliterator());
        List<NoteModel> noteModelList = noteRepository.findAllBy();

        List<NoteDto> noteDtoList = new ArrayList<>();

        for (NoteModel noteModel : noteModelList) {
            noteDtoList.add((new ModelMapper().map(noteModel, NoteDto.class)));
        }

        return ResponseEntity.ok(noteDtoList);
    }
}
