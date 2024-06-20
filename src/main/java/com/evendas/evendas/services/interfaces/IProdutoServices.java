package com.evendas.evendas.services.interfaces;

import com.evendas.evendas.models.produto.ProdutoDTO;
import com.evendas.evendas.utils.models.ResponseObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public interface IProdutoServices {

    List<ProdutoDTO> getAll()throws Exception;

    ArrayList<ResponseObject> listAll(UUID idEstoque) throws Exception;

    boolean hasProdutosDisponiveis()throws Exception;

    ProdutoDTO getOneById(UUID id) throws Exception;

    void delete(UUID idProduto)throws Exception;

    void save(ProdutoDTO imposto)throws Exception;
}
