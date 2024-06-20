package com.evendas.evendas.models.anuncio;

import com.evendas.evendas.models.BaseEntity;
import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.models.imposto.Imposto;
import com.evendas.evendas.models.taxa.Taxa;
import com.evendas.evendas.utils.routines.Routines;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
public class Anuncio extends BaseEntity {

    private float valorVenda;

    @ManyToOne
    @JoinColumn(name = "produto_id") // Coluna de chave estrangeira na tabela Anuncio
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "imposto_id") // Coluna de chave estrangeira na tabela Anuncio
    private Imposto imposto;

    @ManyToOne
    @JoinColumn(name = "taxa_id") // Coluna de chave estrangeira na tabela Anuncio
    private Taxa taxa;

    private float frete;


    public Anuncio() {
    }

    public Anuncio(UUID id, float valorVenda, Taxa taxa, Produto produto, Imposto imposto, float frete) {
        this.id = id;
        this.valorVenda = valorVenda;
        this.taxa = taxa;
        this.produto = produto;
        this.imposto = imposto;
        this.frete = frete;

        if (id == null)
            this.setDataCadastro(LocalDateTime.now());
        else
            this.setDataAlteracao(LocalDateTime.now());
    }

//    public String getMargem(){
//
//        float valor = this.getValorVenda()-(this.getValorVenda()*(this.getImposto().getValor()/100));
//        valor = valor - this.getFrete();
//        valor = valor - (valor *(this.getTaxa().getPorcentagemFinal()/100));
//        valor = valor - this.getProduto().getPreco();
//
//        valor = (valor*100)/this.getValorVenda();
//
//        return Routines.percentFormatterFloatToString(valor);
//    }
}
