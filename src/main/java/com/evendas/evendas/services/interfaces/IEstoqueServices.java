package com.evendas.evendas.services.interfaces;

import com.evendas.evendas.models.estoque.EstoqueDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IEstoqueServices {

    List<EstoqueDTO> getAll()throws Exception;

    String getTotalValorEstoque()throws Exception;

    EstoqueDTO getOneById(UUID id) throws Exception;

    void delete(UUID idEstoque)throws Exception;

    void save(EstoqueDTO estoque)throws Exception;
}
