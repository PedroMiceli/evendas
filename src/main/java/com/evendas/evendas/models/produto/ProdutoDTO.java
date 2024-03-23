package com.evendas.evendas.models.produto;

import com.evendas.evendas.models.imposto.Imposto;
import com.evendas.evendas.utils.routines.Routines;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO extends Produto{

    private String precoStr;

    public ProdutoDTO() {}

    public ProdutoDTO(Produto produto) {
        this.setId(produto.getId());
        this.setProduto(produto.getProduto());
        this.setPrecoStr(Routines.floatStringFormatter(produto.getPreco()));
        this.setSku(produto.getSku());
    }

    public Produto originalObject()throws Exception{
        this.checkNull();
        this.formatter();

        return new Produto(
                this.getId(),
                this.getProduto(),
                this.getSku(),
                this.getPreco()
        );
    }

    public void checkNull()throws Exception{
        if (this.getPrecoStr() == null || this.getPrecoStr().isBlank() || this.getPrecoStr().isEmpty()){
            throw new Exception("Preencha o valor.");
        }

        if (this.getProduto() == null || this.getProduto().isBlank() || this.getProduto().isEmpty()){
            throw new Exception("Preencha o nome do produto.");
        }

        if (this.getSku() == null || this.getSku().isBlank() || this.getSku().isEmpty()){
            throw new Exception("Preencha o SKU do produto.");
        }
    }

    public void formatter(){
        this.setPreco(Routines.floatFormatter(this.getPrecoStr()));
    }
}
