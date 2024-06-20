package com.evendas.evendas.services.interfaces;


import com.evendas.evendas.models.imposto.ImpostoDTO;
import com.evendas.evendas.utils.models.ResponseObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public interface IImpostoServices {

    List<ImpostoDTO> getAll()throws Exception;

    ImpostoDTO getOneById(UUID id) throws Exception;

    ArrayList<ResponseObject> listAll(UUID idImposto) throws Exception;

    void delete(UUID idImposto)throws Exception;

    void save(ImpostoDTO imposto)throws Exception;
}
