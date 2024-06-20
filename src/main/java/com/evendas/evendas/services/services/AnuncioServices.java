package com.evendas.evendas.services.services;

import com.evendas.evendas.models.anuncio.Anuncio;
import com.evendas.evendas.models.anuncio.AnuncioDTO;
import com.evendas.evendas.repository.AnuncioRepository;
import com.evendas.evendas.services.interfaces.IAnuncioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class AnuncioServices implements IAnuncioServices {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Override
    public List<AnuncioDTO> getAll() throws Exception {
        try {
            List<AnuncioDTO> anuncioDTOS = new ArrayList<>();

            for (Anuncio anuncio:anuncioRepository.findAllByDataExcluidoIsNull()) {
                anuncioDTOS.add(new AnuncioDTO(anuncio));
            }
            return anuncioDTOS;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public AnuncioDTO getOneById(UUID id) throws Exception {
        try {
            return new AnuncioDTO(anuncioRepository.findById(id).get());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void delete(UUID idAnuncio) throws Exception {

    }

    @Override
    public void save(AnuncioDTO anuncio) throws Exception {
        try {
            anuncioRepository.save(anuncio.originalObject());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
