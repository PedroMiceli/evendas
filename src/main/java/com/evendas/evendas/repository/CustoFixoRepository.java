package com.evendas.evendas.repository;

import com.evendas.evendas.models.custosFixos.CustoFixo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustoFixoRepository extends JpaRepository<CustoFixo, UUID> {
    List<CustoFixo> findAllByDataExcluidoIsNull()throws Exception;
}
