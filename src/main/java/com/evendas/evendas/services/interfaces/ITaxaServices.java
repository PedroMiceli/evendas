package com.evendas.evendas.services.interfaces;

import com.evendas.evendas.models.valores.Imposto;
import com.evendas.evendas.models.valores.Taxa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ITaxaServices {
    List<Taxa> getAll()throws Exception;

    Taxa getOneById(UUID id) throws Exception;

    void delete(UUID idTaxa)throws Exception;

    void save(Taxa taxa)throws Exception;
}
