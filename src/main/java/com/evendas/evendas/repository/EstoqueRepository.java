package com.evendas.evendas.repository;

import com.evendas.evendas.models.estoque.Estoque;
import com.evendas.evendas.models.valores.Imposto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {
    List<Estoque> findAllByDataExcluidoIsNull()throws Exception;
}
