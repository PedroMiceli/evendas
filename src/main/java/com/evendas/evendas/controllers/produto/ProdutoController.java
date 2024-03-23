package com.evendas.evendas.controllers.produto;

import com.evendas.evendas.controllers.BaseController;
import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.models.produto.ProdutoDTO;
import com.evendas.evendas.services.interfaces.IProdutoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController extends BaseController {

    @Autowired
    private IProdutoServices produtoServices;

    @GetMapping
    public ModelAndView index()throws Exception {
        ModelAndView modelAndView = new ModelAndView("produtos/index");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(required = false) UUID idProduto)throws Exception {

        ProdutoDTO produto = new ProdutoDTO();
        if (idProduto != null)
            produto =produtoServices.getOneById(idProduto);

        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }

    @GetMapping("/getAll")
    public ModelAndView getAll()throws Exception{
        try {
            ModelAndView modelAndView = new ModelAndView("produtos/list");
            modelAndView.addObject("produtos", produtoServices.getAll());
            return modelAndView;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("produto") String produtoJson) throws Exception {
        try {
            ProdutoDTO produto = new ObjectMapper().readValue(produtoJson, ProdutoDTO.class);
            produtoServices.save(produto);

            return responseOk("Salvo com sucesso!");
        } catch (Exception ex) {
            return responseError(ex);
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam UUID idProduto)throws Exception{
        try {
            produtoServices.delete(idProduto);
            return responseOk("Excluído com sucesso!");
        }catch (Exception ex){
            return responseError(ex);
        }
    }

}
