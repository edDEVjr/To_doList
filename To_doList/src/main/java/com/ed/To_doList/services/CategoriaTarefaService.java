package com.ed.To_doList.services;

import com.ed.To_doList.domains.Categoria;
import com.ed.To_doList.domains.Tarefa;
import com.ed.To_doList.repositories.CategoriaRepository;
import com.ed.To_doList.repositories.TarefaRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class CategoriaTarefaService {
    private CategoriaRepository categoriaRepository;
    private TarefaRepository tarefaRepository;
    private CategoriaService categoriaService;
    private TarefaService tarefaService;

    public CategoriaTarefaService(CategoriaRepository categoriaRepository, TarefaRepository tarefaRepository, CategoriaService categoriaService, TarefaService tarefaService) {

        this.categoriaRepository = categoriaRepository;
        this.tarefaRepository = tarefaRepository;
        this.categoriaService = categoriaService;
        this.tarefaService = tarefaService;
    }

    public void addTarefasInCategoria(UUID categoriaId, UUID tarefaId) {
        try{
            Categoria categoria = categoriaService.findById(categoriaId);
            Tarefa tarefa = tarefaService.findById(tarefaId);
            tarefa.setCategoria(categoria);
            categoria.getTarefas().add(tarefa);
            this.categoriaRepository.save(categoria);
            this.tarefaRepository.save(tarefa);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void removeTarefaInCategoria(UUID tarefaId,UUID categoriaId) {
        try{
            Tarefa tarefa = tarefaService.findById(tarefaId);
            tarefa.setCategoria(null);
            Categoria categoria = categoriaService.findById(categoriaId);
            categoria.getTarefas().remove(tarefa);
            this.categoriaRepository.save(categoria);
            this.tarefaRepository.save(tarefa);

        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public List<Tarefa> findByCategoria(Categoria categoria){
        try{
            return this.tarefaRepository.findAllByCategoria_Id(categoria.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Tarefa> findAllMenosCategoria(Categoria categoria){
        List<Tarefa> tarefas = this.tarefaService.findAll();
        List<Tarefa> tarefasAll = this.tarefaRepository.findAll();
        List<Tarefa> tarefasCategoria = this.findByCategoria(categoria);
        for(Tarefa tarefa : tarefasAll){
            for(Tarefa tarefa2 : tarefasCategoria) {
                if (tarefa == tarefa2 ) {
                    tarefas.remove(tarefa);
                }
            }
        }
        return tarefas;
    }







}
