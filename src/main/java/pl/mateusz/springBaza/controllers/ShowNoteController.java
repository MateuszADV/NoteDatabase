package pl.mateusz.springBaza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.mateusz.springBaza.models.NoteModel;
import pl.mateusz.springBaza.models.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ShowNoteController {

    @Autowired
    NoteRepository noteRepository;

    private List<NoteModel> noteLista=new ArrayList<>();
    private List<NoteModel> noteLista1=new ArrayList<>();

    @GetMapping("/shownote")
    public String showNoteGet(Model model){
        noteLista = noteRepository.findAllBy();

        model.addAttribute("lista",noteLista);
        return "shownote";
    }

    @PostMapping("/shownote")                         //  required = false możliwość zczytanie jednego parametru z formulaza
    public String showNotePost(@RequestParam(value = "country",required = false) String someCountry,
                               @RequestParam(value = "deleteId",required = false) String someDeleteId,
                               Model model){

        noteLista = noteRepository.findByCountry(someCountry);
        int countSherchCountry = noteRepository.countAllByCountry(someCountry); //Zwrca liczbe wyszukanych elementów w bazie

        model.addAttribute("country",someCountry);
        model.addAttribute("countCountry",countSherchCountry); //Wysyła liczbę wyszukanych elementów w bazie na stronę

        model.addAttribute("lista", noteLista);

        if(noteLista.isEmpty()) {
            model.addAttribute("brak", "Brak podanego państwa w bazie");
            noteLista = noteRepository.findAllBy();
            Collections.sort(noteLista); //Sortowanie Alfabetycznie po Country
            model.addAttribute("delId", someDeleteId);
            model.addAttribute("lista", noteLista);
            //return "redirect:/shownote";
        }
            try{
                noteRepository.delete(Integer.parseInt(someDeleteId));
                model.addAttribute("idId","Podany rekord został pomyślnie usunięty: "+someDeleteId);

                noteLista = noteRepository.findAllBy();
                model.addAttribute("lista",noteLista);
            }catch (Exception e) {
                model.addAttribute("idId", "Podanego Id już nie ma lub zostało usuniete/lub nie poprawne ID" + someDeleteId);
            }

        return "/shownote";
    }
   /* @PostMapping("/shownote")
    public String deleteId(@RequestParam("deleteId") int someDeleteId,
                           @RequestParam("country") String someCountry,
                           Model model){
        banknotRepository.delete(someDeleteId);
        model.addAttribute("id",someDeleteId);
        model.addAttribute("kraj", "powinien byc kraj");
        return "redirect:/shownote";
    }*/


}
