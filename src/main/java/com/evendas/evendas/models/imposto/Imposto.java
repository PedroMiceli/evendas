package com.evendas.evendas.models.imposto;

import com.evendas.evendas.models.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class Imposto extends BaseEntity {

    private String descricao;

    private float valor;

    public Imposto() {
    }

    public Imposto(String id) {
        this.setId(UUID.fromString(id));
    }

    public Imposto(UUID id, String descricao, float valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;

        if (id == null)
            this.setDataCadastro(LocalDateTime.now());
        else
            this.setDataAlteracao(LocalDateTime.now());
    }

    public Imposto(UUID id) {
        this.setId(id);
    }
}
