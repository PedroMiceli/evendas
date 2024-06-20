package com.evendas.evendas.models.enums;

import lombok.Getter;

public class EnumAnuncios {

    public enum tipoAnuncio {
        CLASSICO("Classico"),
        PREMIUM("PREMIUM");

        @Getter
        private String descricao;
        tipoAnuncio(String descricao){
            this.descricao = descricao;
        }
    }
}
