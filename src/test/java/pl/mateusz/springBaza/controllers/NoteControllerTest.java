package pl.mateusz.springBaza.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mateusz.springBaza.models.NoteModel;
import pl.mateusz.springBaza.models.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc

public class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NoteRepository noteRepository;

    @Test
    public void allNotetest() throws Exception{
        List<NoteModel> lists = new ArrayList<>();
        lists.add(new NoteModel("PLN/2018/01/04/0001","Polska","PLN","Złoty",10.0,"2012",10.0,12.50,1,"UNC","Opis banknotu"));

        Mockito.when(noteRepository.findAll())
                .thenReturn(lists);
        //mockMvc.perform(MockMvcRequestBuilders.get("/rest/shownote"))
        mockMvc.perform(MockMvcRequestBuilders.get("/api/country"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().json("[{\"id\":null,\"numberCatalog\":\"PLN/2018/01/04/0001\",\"country\":\"Polska\",\"cod\":\"PLN\",\"currency\":\"Złoty\",\"denomination\":10.0,\"dataNote\":\"2012\",\"priceBuy\":10.0,\"priceSell\":12.5,\"quantity\":1,\"quality\":\"UNC\",\"notes\":\"Opis banknotu\",\"pathNoteSizeA\":null,\"pathNoteSizeB\":null}]"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
    }

}