package com.evendas.evendas.services.interfaces;

import com.evendas.evendas.models.estoque.Estoque;
import com.evendas.evendas.models.produto.Produto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IEstoqueServices {

    List<Estoque> getAll()throws Exception;

    float getTotalValorEstoque()throws Exception;

    Estoque getOneById(UUID id) throws Exception;

    void delete(UUID idEstoque)throws Exception;

    void save(Estoque estoque)throws Exception;
}
