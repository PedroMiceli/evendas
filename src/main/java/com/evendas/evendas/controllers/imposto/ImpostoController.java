package com.evendas.evendas.controllers.imposto;

import com.evendas.evendas.controllers.BaseController;
import com.evendas.evendas.models.imposto.Imposto;
import com.evendas.evendas.models.imposto.ImpostoDTO;
import com.evendas.evendas.services.interfaces.IImpostoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RestController
@RequestMapping("/imposto")
public class ImpostoController extends BaseController {

    @Autowired
    private IImpostoServices impostoServices;

    @GetMapping
    public ModelAndView index()throws Exception {
        ModelAndView modelAndView = new ModelAndView("impostos/index");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(required = false) UUID idImposto)throws Exception {

        ImpostoDTO imposto = new ImpostoDTO();
        if (idImposto != null)
            imposto =impostoServices.getOneById(idImposto);

        ModelAndView modelAndView = new ModelAndView("impostos/form");
        modelAndView.addObject("imposto", imposto);
        return modelAndView;
    }

    @GetMapping("/getAll")
    public ModelAndView getAll()throws Exception{
        try {
            ModelAndView modelAndView = new ModelAndView("impostos/list");
            modelAndView.addObject("impostos", impostoServices.getAll());
            return modelAndView;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("imposto") String impostoJson) throws Exception {
        try {
            ImpostoDTO imposto = new ObjectMapper().readValue(impostoJson, ImpostoDTO.class);
            impostoServices.save(imposto);

            return responseOk("Salvo com sucesso!");
        } catch (Exception ex) {
            return responseError(ex);
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam UUID idImposto)throws Exception{
        try {
            impostoServices.delete(idImposto);
            return responseOk("Excluído com sucesso!");
        }catch (Exception ex){
            return responseError(ex);
        }
    }

}
