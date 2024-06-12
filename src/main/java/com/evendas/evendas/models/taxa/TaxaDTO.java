package com.evendas.evendas.models.taxa;

import com.evendas.evendas.models.custosFixos.CustoFixo;
import com.evendas.evendas.utils.routines.Routines;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TaxaDTO extends Taxa{

    private String porcentagemInicialStr;
    private String porcentagemFinalStr;

    public TaxaDTO() {}

    public TaxaDTO(String id) {this.setId(UUID.fromString(id));}

    public TaxaDTO(Taxa taxa) {
        this.setId(taxa.getId());
        this.setNome(taxa.getNome());
        this.porcentagemInicialStr = Routines.percentFormatterFloatToString(taxa.getPorcentagemInicial());
        this.porcentagemFinalStr = Routines.percentFormatterFloatToString(taxa.getPorcentagemFinal());
        this.setPorcentagemFinal(taxa.getPorcentagemFinal());
    }

    public Taxa originalObject()throws Exception{
        this.checkNull();
        this.formatter();

        return new Taxa(
                this.getId(),
                this.getNome(),
                this.getPorcentagemInicial(),
                this.getPorcentagemFinal()
        );
    }

    public void checkNull()throws Exception{
        if (this.getPorcentagemFinalStr() == null || this.getPorcentagemFinalStr().isBlank() || this.getPorcentagemFinalStr().isEmpty()){
            throw new Exception("Preencha a data final.");
        }

        if (this.getPorcentagemInicialStr() == null || this.getPorcentagemInicialStr().isBlank() || this.getPorcentagemInicialStr().isEmpty()){
            throw new Exception("Preencha a data inicial.");
        }

        if (this.getNome() == null || this.getNome().isBlank() || this.getNome().isEmpty()){
            throw new Exception("Preencha o nome.");
        }
    }

    public void formatter(){
        this.setPorcentagemFinal(Routines.percentFormatterStringToFloat(this.getPorcentagemFinalStr()));
        this.setPorcentagemInicial(Routines.percentFormatterStringToFloat(this.getPorcentagemInicialStr()));
    }
}
