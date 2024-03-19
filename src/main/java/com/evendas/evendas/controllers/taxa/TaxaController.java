package com.evendas.evendas.controllers.taxa;

import com.evendas.evendas.controllers.BaseController;
import com.evendas.evendas.models.custosFixos.CustoFixo;
import com.evendas.evendas.models.valores.Taxa;
import com.evendas.evendas.services.interfaces.ITaxaServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;


@RestController
@RequestMapping("/taxa")
public class TaxaController extends BaseController {

    @Autowired
    private ITaxaServices taxaServices;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(required = false) UUID idTaxa)throws Exception {

        Taxa taxa = new Taxa();
        if (idTaxa != null)
            taxa =taxaServices.getOneById(idTaxa);

        ModelAndView modelAndView = new ModelAndView("taxa/form");
        modelAndView.addObject("taxa", taxa);
        return modelAndView;
    }

    @GetMapping("/getAll")
    public ModelAndView getAll()throws Exception{
        try {
            ModelAndView modelAndView = new ModelAndView("taxas/list");
            modelAndView.addObject("taxas", taxaServices.getAll());
            return modelAndView;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("taxa") String taxaJson) throws Exception {
        try {
            Taxa taxa = new ObjectMapper().readValue(taxaJson, Taxa.class);
            taxaServices.save(taxa);

            return responseOk("Salvo com sucesso!");
        } catch (Exception ex) {
            return responseError(ex);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam UUID idTaxa)throws Exception{
        try {
            taxaServices.delete(idTaxa);
            return responseOk("Excluído com sucesso!");
        }catch (Exception ex){
            return responseError(ex);
        }
    }

}
