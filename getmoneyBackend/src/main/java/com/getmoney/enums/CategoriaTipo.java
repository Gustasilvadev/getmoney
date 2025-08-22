package com.getmoney.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaTipo {
    Despesa(0),
    Receita(1);

    private final int codigo;

    CategoriaTipo(int codigo) {
        this.codigo = codigo;
    }
    //Objeto vai para JSON, usa o número em vez do nome
    @JsonValue
    public int getCodigo() {
        return codigo;
    }

    //Quando JSON chega com número, converte para o objeto enum
    @JsonCreator
    public static CategoriaTipo fromCodigo(int codigo) {
        for (CategoriaTipo tipo : CategoriaTipo.values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
