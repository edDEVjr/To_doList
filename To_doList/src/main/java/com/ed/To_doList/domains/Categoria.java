package com.ed.To_doList.domains;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;


    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private Set<Tarefa> tarefas;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(Set<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
