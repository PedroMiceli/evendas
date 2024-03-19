package com.evendas.evendas.repository;

import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.models.valores.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaxaRepository extends JpaRepository<Taxa, UUID> {
    List<Taxa> findAllByDataExcluidoIsNull()throws Exception;
}
