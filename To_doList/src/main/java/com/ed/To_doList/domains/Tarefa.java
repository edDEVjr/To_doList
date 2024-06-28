package com.ed.To_doList.domains;

import jakarta.persistence.*;


import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name ="tarefa")
public class Tarefa {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    boolean concluida = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="categoria_id")
    private Categoria categoria;


    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = !concluida;
   }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
