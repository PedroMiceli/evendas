package com.evendas.evendas.services.services;

import com.evendas.evendas.models.valores.Imposto;
import com.evendas.evendas.models.valores.Taxa;
import com.evendas.evendas.repository.TaxaRepository;
import com.evendas.evendas.services.interfaces.ITaxaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaxaServices implements ITaxaServices {

    @Autowired
    private TaxaRepository taxaRepository;

    @Override
    public List<Taxa> getAll() throws Exception {
        try {
            return taxaRepository.findAllByDataExcluidoIsNull();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Taxa getOneById(UUID id) throws Exception {
        try {
            return taxaRepository.findById(id).get();
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
    public void save(Taxa taxa) throws Exception {
        try {
            taxaRepository.save(taxa);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
