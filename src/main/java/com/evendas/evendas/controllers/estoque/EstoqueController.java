package com.evendas.evendas.controllers.estoque;

import com.evendas.evendas.controllers.BaseController;
import com.evendas.evendas.models.BaseEntity;
import com.evendas.evendas.models.estoque.Estoque;
import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.services.interfaces.IEstoqueServices;
import com.evendas.evendas.services.interfaces.IProdutoServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RestController
@RequestMapping("/estoque")
public class EstoqueController extends BaseController {

    @Autowired
    private IEstoqueServices estoqueServices;

    @Autowired
    private IProdutoServices produtoServices;

    @GetMapping
    public ModelAndView index()throws Exception {
        ModelAndView modelAndView = new ModelAndView("estoque/index");
        modelAndView.addObject("hasProdutosDisponiveis", produtoServices.hasProdutosDisponiveis());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam(required = false) UUID idEstoque)throws Exception {

        Estoque estoque = new Estoque();
        if (idEstoque != null)
            estoque =estoqueServices.getOneById(idEstoque);

        ModelAndView modelAndView = new ModelAndView("estoque/form");
        modelAndView.addObject("estoque", estoque);
        modelAndView.addObject("produtos", produtoServices.listAll(idEstoque));
        return modelAndView;
    }

    @GetMapping("/getAll")
    public ModelAndView getAll()throws Exception{
        try {
            ModelAndView modelAndView = new ModelAndView("estoque/list");
            modelAndView.addObject("estoques", estoqueServices.getAll());
            return modelAndView;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("estoque") String estoqueJson) throws Exception {
        try {
            Estoque estoque = new ObjectMapper().readValue(estoqueJson, Estoque.class);
            estoqueServices.save(estoque);

            return responseOk("Salvo com sucesso!");
        } catch (Exception ex) {
            return responseError(ex);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam UUID idEstoque)throws Exception{
        try {
            estoqueServices.delete(idEstoque);
            return responseOk("Excluído com sucesso!");
        }catch (Exception ex){
            return responseError(ex);
        }
    }

}
