package com.evendas.evendas.controllers.custoFixo;

import com.evendas.evendas.controllers.BaseController;
import com.evendas.evendas.models.custosFixos.CustoFixoDTO;
import com.evendas.evendas.services.interfaces.ICustoFixoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RestController
@RequestMapping("/custoFixo")
public class CustoFixoController extends BaseController {

    @Autowired
    private ICustoFixoServices custoFixoServices;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(required = false) UUID idCustoFixo)throws Exception {

        CustoFixoDTO custoFixo = new CustoFixoDTO();
        if (idCustoFixo != null)
            custoFixo =custoFixoServices.getOneById(idCustoFixo);

        ModelAndView modelAndView = new ModelAndView("custoFixo/form");
        modelAndView.addObject("custoFixo", custoFixo);
        return modelAndView;
    }

    @GetMapping("/getAll")
    public ModelAndView getAll()throws Exception{
        try {
            ModelAndView modelAndView = new ModelAndView("custoFixo/list");
            modelAndView.addObject("custosFixos", custoFixoServices.getAll());
            return modelAndView;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("custoFixo") String custoFixoJson) throws Exception {
        try {
            CustoFixoDTO custoFixo = new ObjectMapper().readValue(custoFixoJson, CustoFixoDTO.class);
            custoFixoServices.save(custoFixo.originalObject());

            return responseOk("Salvo com sucesso!");
        } catch (Exception ex) {
            return responseError(ex);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam UUID idCustoFixo)throws Exception{
        try {
            custoFixoServices.delete(idCustoFixo);
            return responseOk("Excluído com sucesso!");
        }catch (Exception ex){
            return responseError(ex);
        }
    }


}
