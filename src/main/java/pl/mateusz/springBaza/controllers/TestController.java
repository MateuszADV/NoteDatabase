package pl.mateusz.springBaza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mateusz.springBaza.models.NoteModel;
import pl.mateusz.springBaza.models.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    NoteRepository noteRepository;

    private List<NoteModel> noteLista=new ArrayList<>();
    NoteModel noteModel = new NoteModel();

    @GetMapping("/test")
    public String testGet(Model model){


        return "test";
    }

   /* public String nextData(){

    }

    public String nextNumber(String afterNumber){
        String number= afterNumber.substring(afterNumber.length()-4,afterNumber.length());
        int nextNumber = Integer.parseInt(number)+1;
        number=String.valueOf(nextNumber);

        while (number.length()<5){
            number="0"+number;
        }
        if (number.length()==5){
            number="/"+number;
        }
        return number;
    }*/

}
