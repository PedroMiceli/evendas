package com.evendas.evendas.models.taxa;

import com.evendas.evendas.models.BaseEntity;
import com.evendas.evendas.models.anuncio.Anuncio;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class Taxa extends BaseEntity {

    private String nome;

    private float porcentagemInicial;

    private float porcentagemFinal;

    @OneToMany(mappedBy = "taxa", fetch = FetchType.LAZY)
    private List<Anuncio> anuncios;

    public Taxa() {
    }

    public Taxa(UUID id, String nome, float porcentagemInicial, float porcentagemFinal) {
        this.id = id;
        this.nome = nome;
        this.porcentagemInicial = porcentagemInicial;
        this.porcentagemFinal = porcentagemFinal;

        if (id == null)
            this.setDataCadastro(LocalDateTime.now());
        else
            this.setDataAlteracao(LocalDateTime.now());
    }

    public Taxa(UUID id) {
        this.setId(id);
    }
}
