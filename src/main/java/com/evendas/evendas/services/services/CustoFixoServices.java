package com.evendas.evendas.services.services;

import com.evendas.evendas.models.custosFixos.CustoFixo;
import com.evendas.evendas.models.custosFixos.CustoFixoDTO;
import com.evendas.evendas.repository.CustoFixoRepository;
import com.evendas.evendas.services.interfaces.ICustoFixoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustoFixoServices implements ICustoFixoServices {

    @Autowired
    private CustoFixoRepository custoFixoRepository;

    @Override
    public List<CustoFixoDTO> getAll() throws Exception {
        try {
            List<CustoFixoDTO> custos = new ArrayList<>();

            for (CustoFixo custoFixo:custoFixoRepository.findAllByDataExcluidoIsNull()) {
                custos.add(new CustoFixoDTO(custoFixo));
            }

            return custos;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public CustoFixoDTO getOneById(UUID id) throws Exception {
        try {
            return new CustoFixoDTO(custoFixoRepository.findById(id).get());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void delete(UUID idCustoFixo) throws Exception {
        try {
            CustoFixo custoFixo = getOneById(idCustoFixo);
            custoFixo.changeExcludedDate();
            custoFixoRepository.save(custoFixo);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void save(CustoFixo custoFixo) throws Exception {
        try {
            custoFixoRepository.save(custoFixo);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
