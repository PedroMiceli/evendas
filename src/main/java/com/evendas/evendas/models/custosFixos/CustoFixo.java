package com.evendas.evendas.models.custosFixos;

import com.evendas.evendas.models.BaseEntity;
import com.evendas.evendas.utils.routines.Routines;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class CustoFixo extends BaseEntity {

    private String nome;

    private float custoMensal;

    public CustoFixo() {
    }

    public CustoFixo(UUID id, String nome, Float custoMensal) {
        this.setId(id);
        this.nome = nome;
        this.custoMensal = custoMensal;

        if (id == null)
            this.setDataCadastro(LocalDateTime.now());
        else
            this.setDataAlteracao(LocalDateTime.now());
    }


}
