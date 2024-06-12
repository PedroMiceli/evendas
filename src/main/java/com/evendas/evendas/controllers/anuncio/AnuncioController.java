package com.evendas.evendas.controllers.anuncio;

import com.evendas.evendas.controllers.BaseController;
import com.evendas.evendas.models.anuncio.AnuncioDTO;
import com.evendas.evendas.models.estoque.EstoqueDTO;
import com.evendas.evendas.services.interfaces.IAnuncioServices;
import com.evendas.evendas.services.interfaces.IImpostoServices;
import com.evendas.evendas.services.interfaces.IProdutoServices;
import com.evendas.evendas.services.interfaces.ITaxaServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController extends BaseController {

    @Autowired
    private IAnuncioServices  anuncioServices;

    @Autowired
    private IProdutoServices produtoServices;

    @Autowired
    private IImpostoServices impostoServices;

    @Autowired
    private ITaxaServices taxaServices;


    @GetMapping
    public ModelAndView index()throws Exception {
        return new ModelAndView("anuncios/index");
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(required = false) UUID idAnuncio)throws Exception {

        AnuncioDTO anuncioDTO = new AnuncioDTO();
        if (idAnuncio != null)
            anuncioDTO =anuncioServices.getOneById(idAnuncio);

        ModelAndView modelAndView = new ModelAndView("anuncios/form");
        modelAndView.addObject("anuncio", anuncioDTO);
        modelAndView.addObject("taxas", taxaServices.listAll(idAnuncio));
        modelAndView.addObject("impostos", impostoServices.listAll(idAnuncio));
        modelAndView.addObject("produtos", produtoServices.listAll(idAnuncio));
        return modelAndView;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("anuncio") String anuncioJson) throws Exception {
        try {
            AnuncioDTO anuncio = new ObjectMapper().readValue(anuncioJson, AnuncioDTO.class);
            anuncioServices.save(anuncio);

            return responseOk("Salvo com sucesso!");
        } catch (Exception ex) {
            return responseError(ex);
        }
    }

    @GetMapping("/getAll")
    public ModelAndView getAll()throws Exception{
        try {
            ModelAndView modelAndView = new ModelAndView("anuncios/list");
            modelAndView.addObject("anuncios", anuncioServices.getAll());
            return modelAndView;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
