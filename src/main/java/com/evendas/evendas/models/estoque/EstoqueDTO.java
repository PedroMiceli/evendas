package com.evendas.evendas.models.estoque;

import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.models.produto.ProdutoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueDTO extends Estoque{

    private ProdutoDTO produtoDTO;

    public EstoqueDTO() {
    }

    public EstoqueDTO(Estoque estoque) {
        this.setId(estoque.getId());
        this.setQuantidade(estoque.getQuantidade());
        this.setProdutoDTO(new ProdutoDTO(estoque.getProduto()));
        this.setProduto(estoque.getProduto());
    }

    public Estoque originalObject()throws Exception{
        this.checkNull();
        return new Estoque(
                this.getId(),
                this.getQuantidade(),
                new Produto(this.getProdutoDTO().getId())
        );
    }

    public void checkNull()throws Exception{
        if (this.getProdutoDTO() == null || this.getProdutoDTO().getId() == null){
            throw new Exception("Escolha o produto");
        }

        if (this.getQuantidade() == 0){
            throw new Exception("Preencha a quantidade do produto.");
        }
    }
}
