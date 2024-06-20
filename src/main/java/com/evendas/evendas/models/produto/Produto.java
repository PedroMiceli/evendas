package com.evendas.evendas.models.produto;

import com.evendas.evendas.models.BaseEntity;
import com.evendas.evendas.models.anuncio.Anuncio;
import com.evendas.evendas.models.estoque.Estoque;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class Produto extends BaseEntity {

    private String produto;

    private String sku;

    private float preco;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Anuncio> anuncios;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
    private Estoque estoque;

    public Produto() {
    }

    public Produto(String id) {
        this.id = UUID.fromString(id);
    }

    public Produto(UUID id, String produto, String sku, float preco) {
        this.id = id;
        this.produto = produto;
        this.sku = sku;
        this.preco = preco;


        if (id == null)
            this.setDataCadastro(LocalDateTime.now());
        else
            this.setDataAlteracao(LocalDateTime.now());
    }

    public Produto(UUID id) {
        this.setId(id);
    }
}
