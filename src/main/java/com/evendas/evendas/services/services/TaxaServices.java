package com.evendas.evendas.services.services;

import com.evendas.evendas.models.taxa.Taxa;
import com.evendas.evendas.models.taxa.TaxaDTO;
import com.evendas.evendas.repository.TaxaRepository;
import com.evendas.evendas.services.interfaces.ITaxaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaxaServices implements ITaxaServices {

    @Autowired
    private TaxaRepository taxaRepository;

    @Override
    public List<TaxaDTO> getAll() throws Exception {
        try {
            List<TaxaDTO> taxas = new ArrayList<>();

            for (Taxa taxa : taxaRepository.findAllByDataExcluidoIsNull()) {
                taxas.add(new TaxaDTO(taxa));
            }
            return taxas;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public TaxaDTO getOneById(UUID id) throws Exception {
        try {
            return new TaxaDTO(taxaRepository.findById(id).get());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void delete(UUID idTaxa) throws Exception {
        try {
            Taxa taxa = getOneById(idTaxa);
            taxa.changeExcludedDate();
            taxaRepository.save(taxa);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void save(TaxaDTO taxa) throws Exception {
        try {
            taxaRepository.save(taxa.originalObject());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
