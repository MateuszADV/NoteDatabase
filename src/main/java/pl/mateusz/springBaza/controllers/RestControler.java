package pl.mateusz.springBaza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mateusz.springBaza.models.repositories.NoteRepository;

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
        noteRepository.delete(someId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/shownote/{country}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity banknoty(@PathVariable("country") String someCountry, Model model){

        return new ResponseEntity(noteRepository.findByCountry(someCountry), HttpStatus.OK);
    }
}
