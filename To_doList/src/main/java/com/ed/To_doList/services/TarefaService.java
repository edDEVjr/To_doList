package com.ed.To_doList.services;

import com.ed.To_doList.domains.Tarefa;
import com.ed.To_doList.dtos.RequisicaoTarefa;
import com.ed.To_doList.repositories.TarefaRepository;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }


    public void create( RequisicaoTarefa requisicao){
        Tarefa newTarefa = new Tarefa();
        newTarefa.setTitulo(requisicao.getTitulo());
        newTarefa.setDescricao(requisicao.getDescricao());
        newTarefa.setData(this.convertLocalDateForSqlDate(requisicao.getData()));
        this.tarefaRepository.save(newTarefa);
    }

    public void update(UUID id,RequisicaoTarefa requisicao){
        Optional<Tarefa> optional = this.tarefaRepository.findById(id);
        if(optional.isPresent()){
            Tarefa tarefa = optional.get();
            tarefa.setTitulo(requisicao.getTitulo());
            tarefa.setDescricao(requisicao.getDescricao());
            tarefa.setData(this.convertLocalDateForSqlDate(requisicao.getData()));
            this.tarefaRepository.save(tarefa);
        }else{
            System.out.println("***************Id inexistente***********");
        }
    }

    public void delete(UUID id){
        Optional<Tarefa> optional = this.tarefaRepository.findById(id);
        if(optional.isPresent()){
            this.tarefaRepository.deleteById(id);
        }else{
            System.out.println("***************Id inexistente***********");
        }

    }

    public List<Tarefa> findAll(){
        return this.tarefaRepository.findAll();
    }


    public Tarefa findById(UUID id){
        Optional<Tarefa> optional = this.tarefaRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            System.out.println("***************Id inexistente***********");
            return null;
        }
    }
    
    public void fromTarefa (Tarefa tarefa,RequisicaoTarefa requisicao){
        requisicao.setTitulo(tarefa.getTitulo());
        requisicao.setDescricao(tarefa.getDescricao());
        requisicao.setData(this.convertSqlDateForLocalDate(tarefa.getData()));
    }

    public void concluida(UUID id){
        Tarefa tarefa = this.findById(id);
        tarefa.setConcluida(tarefa.getConcluida());
        this.tarefaRepository.save(tarefa);
    }



    private Date convertLocalDateForSqlDate(LocalDate localDate){
        return Date.valueOf(localDate);
    }

    private LocalDate convertSqlDateForLocalDate(Date sqlDate){
        return sqlDate.toLocalDate();
    }


}
