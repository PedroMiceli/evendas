package com.evendas.evendas.utils.models;

import lombok.Getter;
import lombok.Setter;

public class ResponseObject {

    @Getter
    @Setter
    private Object value;

    @Getter @Setter
    private String key;

    @Getter @Setter
    private boolean selected;

    public ResponseObject(Object value, String key) {
        this.value = value;
        this.key = key;
        this.selected = false;
    }

    public ResponseObject(Object value, String key, boolean selected) {
        this.value = value;
        this.key = key;
        this.selected = selected;
    }
}