package com.evendas.evendas.services.interfaces;

import com.evendas.evendas.models.anuncio.AnuncioDTO;
import com.evendas.evendas.models.estoque.Estoque;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IAnuncioServices {

    List<AnuncioDTO>  getAll()throws Exception;

    AnuncioDTO getOneById(UUID id)throws Exception;

    void delete(UUID idAnuncio)throws Exception;

    void save(AnuncioDTO anuncio)throws Exception;
}
