package com.getmoney.entity;

import com.getmoney.enums.CategoriaTipo;
import jakarta.persistence.*;

@Entity
@Table(name="categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer id;

    @Column(name="categoria_nome")
    private String nome;

    @Column(name="categoria_tipo")
    private CategoriaTipo tipo;

    /*
    @OneToMany(mappedBy = "transacao")
    private Transacao transacao;



    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }
    */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaTipo getTipo() {
        return tipo;
    }

    public void setTipo(CategoriaTipo tipo) {
        this.tipo = tipo;
    }
}
