package com.evendas.evendas.models.anuncio;

import com.evendas.evendas.models.imposto.Imposto;
import com.evendas.evendas.models.imposto.ImpostoDTO;
import com.evendas.evendas.models.produto.Produto;
import com.evendas.evendas.models.produto.ProdutoDTO;
import com.evendas.evendas.models.taxa.Taxa;
import com.evendas.evendas.models.taxa.TaxaDTO;
import com.evendas.evendas.utils.routines.Routines;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnuncioDTO extends Anuncio{

    private String valorVendaStr;

    private ProdutoDTO produtoDTO;

    private ImpostoDTO impostoDTO;

    private TaxaDTO taxaDTO;

    private String freteStr;

    public AnuncioDTO() {}

    public AnuncioDTO(Anuncio anuncio) {
        this.setId(anuncio.getId());
        this.valorVendaStr = Routines.floatStringFormatter(anuncio.getValorVenda());
        this.produtoDTO = new ProdutoDTO(anuncio.getProduto());
        this.impostoDTO = new ImpostoDTO(anuncio.getImposto());
        this.taxaDTO = new TaxaDTO(anuncio.getTaxa());
        this.freteStr = Routines.floatStringFormatter(anuncio.getFrete());
    }

    public Anuncio originalObject()throws Exception{
        this.checkNull();
        this.formatter();

        return new Anuncio(
                this.getId(),
                this.getValorVenda(),
                new Taxa(this.getTaxaDTO().getId()),
                new Produto(this.getProdutoDTO().getId()),
                new Imposto(this.getImpostoDTO().getId()),
                this.getFrete()
        );
    }

    public void checkNull()throws Exception{
        if (this.getFreteStr() == null || this.getFreteStr().isBlank() || this.getFreteStr().isEmpty()){
            throw new Exception("Preencha o valor do frete.");
        }

        if (this.getValorVendaStr() == null || this.getValorVendaStr().isBlank() || this.getValorVendaStr().isEmpty()){
            throw new Exception("Preencha o valor de venda do produto.");
        }

        if (this.getProdutoDTO() == null || this.getProdutoDTO().getId() == null){
            throw new Exception("Selecione o produto.");
        }

        if (this.getTaxaDTO() == null || this.getTaxaDTO().getId() == null){
            throw new Exception("selecione a taxa.");
        }

        if (this.getImpostoDTO() == null || this.getImpostoDTO().getId() == null){
            throw new Exception("selecione o imposto a ser aplicado.");
        }
    }

    public void formatter(){
        this.setValorVenda(Routines.floatFormatter(this.valorVendaStr));
        this.setValorVenda(Routines.floatFormatter(this.freteStr));
    }
}
