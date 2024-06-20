package com.evendas.evendas.repository;

import com.evendas.evendas.models.imposto.Imposto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImpostoRepository extends JpaRepository<Imposto, UUID> {

    List<Imposto> findAllByDataExcluidoIsNull()throws Exception;
}
