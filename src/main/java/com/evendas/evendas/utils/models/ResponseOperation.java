package com.evendas.evendas.utils.models;

import lombok.Getter;
import lombok.Setter;

public class ResponseOperation {
    @Getter
    @Setter
    private boolean ok;

    @Getter @Setter
    private String message;

    @Getter @Setter
    private Object dados;


    public ResponseOperation() {}

    public ResponseOperation(boolean ok) {
        this.ok = ok;
    }

    public ResponseOperation(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public ResponseOperation(Exception ex) {
        this.ok = false;
        this.message = ex.getMessage().replaceAll("java.lang.Exception:", "");
    }

    public ResponseOperation (boolean ok, Object dados) {
        this.ok = ok;
        this.dados = dados;
    }
}