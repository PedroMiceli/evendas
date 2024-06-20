package com.evendas.evendas.services.services;

import com.evendas.evendas.models.imposto.Imposto;
import com.evendas.evendas.models.imposto.ImpostoDTO;
import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.repository.ImpostoRepository;
import com.evendas.evendas.services.interfaces.IImpostoServices;
import com.evendas.evendas.utils.models.ResponseObject;
import com.evendas.evendas.utils.routines.Routines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImpostoServices implements IImpostoServices {

    @Autowired
    private ImpostoRepository impostoRepository;

    @Override
    public List<ImpostoDTO> getAll() throws Exception {
        try {
            List<ImpostoDTO> impostoDTOS = new ArrayList<>();

            for (Imposto imposto:impostoRepository.findAllByDataExcluidoIsNull()) {
                impostoDTOS.add(new ImpostoDTO(imposto));
            }
            return impostoDTOS;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public ImpostoDTO getOneById(UUID id) throws Exception {
        try {
            return new ImpostoDTO(impostoRepository.findById(id).get());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public ArrayList<ResponseObject> listAll(UUID idImposto) throws Exception {
        try {
            ArrayList<Imposto> impostos = (ArrayList<Imposto>) impostoRepository.findAllByDataExcluidoIsNull();


            ArrayList<ResponseObject> dados = new ArrayList<>();
            for (Imposto imposto: impostos) {
                dados.add(new ResponseObject(imposto.getId(),imposto.getDescricao() + " - " + Routines.percentFormatterFloatToString(imposto.getValor())));
            }

            return dados;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void delete(UUID idImposto) throws Exception {
        try {
            Imposto imposto = getOneById(idImposto);
            imposto.changeExcludedDate();
            impostoRepository.save(imposto);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void save(ImpostoDTO imposto) throws Exception {
        try {
            impostoRepository.save(imposto.originalObject());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
