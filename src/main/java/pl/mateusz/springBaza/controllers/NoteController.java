package pl.mateusz.springBaza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.mateusz.springBaza.models.forms.NoteForm;
import pl.mateusz.springBaza.models.NoteModel;
import pl.mateusz.springBaza.models.repositories.NoteRepository;
import pl.mateusz.springBaza.utils.UtilNote;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    private List<NoteModel> noteLista=new ArrayList<>();
    private List<NoteForm> noteFormList=new ArrayList<>();

    @PostMapping(value = "/note")
    public String notePost(@ModelAttribute("noteForm")@Valid NoteForm form, BindingResult result, Model model){
        noteLista = noteRepository.findAllBy();
        if(result.hasErrors()){
            model.addAttribute("error","Wypełnij poprawnie pola");
            model.addAttribute("nextNumberCatalog", UtilNote.nextData()+UtilNote.nextNumber(chekList(noteLista)));
            //return "redirect:/note";
            return "note";
        }else if (!form.getPathNoteSizeA().equals(form.getPathNoteSizeB())){
            form.setNumberCatalog(form.getCod()+"/"+(UtilNote.nextData()+UtilNote.nextNumber(chekList(noteLista))));
            noteFormList.add(form);

            noteRepository.save(new NoteModel(form));
        }
        model.addAttribute("nextNumberCatalog",UtilNote.nextData()+UtilNote.nextNumber(chekList(noteLista)));
        model.addAttribute("notelist", noteFormList);
        model.addAttribute("pathA",form.getPathNoteSizeA());
        model.addAttribute("pathB",form.getPathNoteSizeB());

        return "note";
    }

    @GetMapping(value = "/note")
    public String noteGet(Model model){
        noteLista = noteRepository.findAllBy();
        chekList(noteLista);

        model.addAttribute("noteForm", new NoteForm());
        model.addAttribute("nextNumberCatalog", UtilNote.nextData()+UtilNote.nextNumber(chekList(noteLista)));

        return "note";
    }


    //Jets to metoda sprawdzająca czy argument otrzymany jako lista jest pusta,
    //Jesli jest pusta zwraca wartość String "0"
    //Jeśli nie jest pusta zwraca ostatni numer katalogowy banknotu z listy jako string
    private String chekList(List<NoteModel> noteLista) {
        if (noteLista.isEmpty()){
            return "0";
        }else {
            return noteLista.get(noteLista.size()-1).getNumberCatalog();
        }
    }

}
