package com.evendas.evendas.models.estoque;

import com.evendas.evendas.models.BaseEntity;
import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.utils.routines.Routines;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class Estoque extends BaseEntity {

    private int quantidade;

    @OneToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    public Estoque() {}

    public Estoque(UUID id, int quantidade, Produto produto) {
        this.setId(id);
        this.quantidade = quantidade;
        this.produto = produto;

        if (id == null)
            this.setDataCadastro(LocalDateTime.now());
        else
            this.setDataAlteracao(LocalDateTime.now());
    }
    public String getValue(){
        return Routines.floatStringFormatter(this.produto.getPreco()*this.quantidade);
    }
}
