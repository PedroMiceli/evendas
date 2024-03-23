package com.evendas.evendas.models.custosFixos;

import com.evendas.evendas.utils.routines.Routines;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustoFixoDTO extends CustoFixo{

    private String custoMensalStr;

    public CustoFixoDTO() {
    }

    public CustoFixoDTO(CustoFixo custoFixo) {
        this.setId(custoFixo.getId());
        this.setNome(custoFixo.getNome());
        this.setCustoMensalStr(Routines.floatStringFormatter(custoFixo.getCustoMensal()));
    }

    public CustoFixo originalObject()throws Exception{
        this.checkNull();
        this.formatter();

        return new CustoFixo(
                this.getId(),
                this.getNome(),
                this.getCustoMensal()
        );
    }

    public void checkNull()throws Exception{
        if (this.getCustoMensalStr().isBlank() || this.getNome().isBlank() || this.getCustoMensalStr() == null || this.getNome() == null){
            throw new Exception("Preencha os dois campos.");
        }
    }

    public void formatter(){
        this.setCustoMensal(Routines.floatFormatter(this.getCustoMensalStr()));
    }
}
