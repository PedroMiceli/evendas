package com.evendas.evendas.services.services;

import com.evendas.evendas.models.produto.Produto;

import com.evendas.evendas.models.produto.ProdutoDTO;
import com.evendas.evendas.repository.ProdutoRepository;
import com.evendas.evendas.services.interfaces.IProdutoServices;
import com.evendas.evendas.utils.models.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ProdutoServices implements IProdutoServices {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDTO> getAll() throws Exception {
        try {
            List<ProdutoDTO>  produtoDTOS = new ArrayList<>();

            for (Produto produto:produtoRepository.findAllByDataExcluidoIsNull()) {
                produtoDTOS.add(new ProdutoDTO(produto));
            }
            return produtoDTOS;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public ArrayList<ResponseObject> listAll(UUID idEstoque) throws Exception {
        try {
            ArrayList<Produto> produtos = (ArrayList<Produto>) produtoRepository.findAllByDataExcluidoIsNull();


            ArrayList<ResponseObject> dados = new ArrayList<>();
            for (Produto produto: produtos) {
                dados.add(new ResponseObject(produto.getId(),produto.getProduto()+" - "+produto.getSku()));
            }

            return dados;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public boolean hasProdutosDisponiveis()throws Exception{
        try {
            return !getAll().stream().filter(item ->  item.getEstoque() == null).toList().isEmpty();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public ProdutoDTO getOneById(UUID id) throws Exception {
        try {
            return new ProdutoDTO(produtoRepository.findById(id).get());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void delete(UUID idProduto) throws Exception {
        try {
            Produto produto = getOneById(idProduto);
            produto.changeExcludedDate();
            produtoRepository.save(produto);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void save(ProdutoDTO produto) throws Exception {
        try {
            produtoRepository.save(produto.originalObject());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
