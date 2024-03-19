package com.evendas.evendas.services.interfaces;

import com.evendas.evendas.models.custosFixos.CustoFixo;
import com.evendas.evendas.models.valores.Imposto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ICustoFixoServices {

    List<CustoFixo> getAll()throws Exception;

    CustoFixo getOneById(UUID id) throws Exception;

    void delete(UUID idImposto)throws Exception;

    void save(CustoFixo custoFixo)throws Exception;
}
