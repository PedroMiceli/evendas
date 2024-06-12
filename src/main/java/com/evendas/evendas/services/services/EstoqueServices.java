package com.evendas.evendas.services.services;

import com.evendas.evendas.models.estoque.Estoque;
import com.evendas.evendas.models.estoque.EstoqueDTO;
import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.repository.EstoqueRepository;
import com.evendas.evendas.repository.ProdutoRepository;
import com.evendas.evendas.services.interfaces.IEstoqueServices;
import com.evendas.evendas.services.interfaces.IProdutoServices;
import com.evendas.evendas.utils.routines.Routines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EstoqueServices implements IEstoqueServices {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private IProdutoServices produtoServices;

    @Override
    public List<EstoqueDTO> getAll() throws Exception {
        try {
            List<EstoqueDTO> estoqueDTOS = new ArrayList<>();

            for (Estoque estoque:estoqueRepository.findAllByDataExcluidoIsNull()) {
                estoqueDTOS.add(new EstoqueDTO(estoque));
            }
            return estoqueDTOS;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public String getTotalValorEstoque() throws Exception {
        try {
            List<EstoqueDTO> estoques = getAll();
            float valorTotal = 0;
            for (EstoqueDTO estoque:estoques) {
                valorTotal = valorTotal + Routines.floatFormatter(estoque.getValue());
            }
            return Routines.floatStringFormatter(valorTotal);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public EstoqueDTO getOneById(UUID id) throws Exception {
        try {
            return new EstoqueDTO(estoqueRepository.findById(id).get());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void delete(UUID idEstoque) throws Exception {
        try {
            Estoque estoque = getOneById(idEstoque);
            Produto produto = produtoServices.getOneById(estoque.getProduto().getId());
            produto.setEstoque(null);
            produtoRepository.save(produto);

            estoque.setProduto(null);
            estoqueRepository.delete(estoque);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void save(EstoqueDTO estoque) throws Exception {
        try {
            estoqueRepository.save(estoque.originalObject());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
