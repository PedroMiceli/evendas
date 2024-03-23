package com.evendas.evendas.services.services;

import com.evendas.evendas.models.estoque.Estoque;
import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.repository.EstoqueRepository;
import com.evendas.evendas.repository.ProdutoRepository;
import com.evendas.evendas.services.interfaces.IEstoqueServices;
import com.evendas.evendas.services.interfaces.IProdutoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
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
    public List<Estoque> getAll() throws Exception {
        try {
            return estoqueRepository.findAllByDataExcluidoIsNull();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public float getTotalValorEstoque() throws Exception {
        try {
            List<Estoque> estoques = getAll();
            float valorTotal = 0;
            for (Estoque estoque:estoques) {
                valorTotal = valorTotal+estoque.getValue();
            }
            return valorTotal;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Estoque getOneById(UUID id) throws Exception {
        try {
            return estoqueRepository.findById(id).get();
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
    public void save(Estoque estoque) throws Exception {
        try {
            estoqueRepository.save(estoque);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
