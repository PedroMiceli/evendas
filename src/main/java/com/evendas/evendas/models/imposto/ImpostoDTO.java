package com.evendas.evendas.models.imposto;

import com.evendas.evendas.utils.routines.Routines;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImpostoDTO extends Imposto {

    private String valorStr;

    public ImpostoDTO() {
    }

    public ImpostoDTO(Imposto imposto) {
        this.setId(imposto.getId());
        this.setDescricao(imposto.getDescricao());
        this.valorStr = Routines.percentFormatterFloatToString(imposto.getValor());
    }

    public Imposto originalObject()throws Exception{
        this.checkNull();
        this.formatter();

        return new Imposto(
                this.getId(),
                this.getDescricao(),
                this.getValor()
        );
    }

    public void checkNull()throws Exception{
        if (this.getValorStr() == null || this.getValorStr().isBlank() || this.getValorStr().isEmpty()){
            throw new Exception("Preencha o valor.");
        }

        if (this.getDescricao() == null || this.getDescricao().isBlank() || this.getDescricao().isEmpty()){
            throw new Exception("Preencha a descrição.");
        }
    }

    public void formatter(){
        this.setValor(Routines.percentFormatterStringToFloat(this.getValorStr()));

    }
}
