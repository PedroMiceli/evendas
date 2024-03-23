package com.evendas.evendas.services.interfaces;

import com.evendas.evendas.models.taxa.TaxaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ITaxaServices {
    List<TaxaDTO> getAll()throws Exception;

    TaxaDTO getOneById(UUID id) throws Exception;

    void delete(UUID idTaxa)throws Exception;

    void save(TaxaDTO taxa)throws Exception;
}
