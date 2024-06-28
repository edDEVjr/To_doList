package com.ed.To_doList.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;


public class RequisicaoTarefa {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String titulo;

    @Size(max = 250)
    private String descricao;

    @NotNull
    private LocalDate data;


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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }



}
