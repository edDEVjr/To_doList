package com.ed.To_doList.controllers;

import com.ed.To_doList.domains.Tarefa;
import com.ed.To_doList.dtos.RequisicaoTarefa;
import com.ed.To_doList.services.TarefaService;
import jakarta.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.UUID;


@Controller()
public class TarefaControlller {

    private TarefaService tarefaService;

    public TarefaControlller(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/tarefas")
    public ModelAndView index(){
       ModelAndView mv = new ModelAndView("tarefas/index");
       mv.addObject("tarefas",tarefaService.findAll());
       return mv;
    }

    @GetMapping("/tarefas/new")
    public ModelAndView nnew(){
        ModelAndView mv =  new ModelAndView("tarefas/new");
        mv.addObject("requisicao",new RequisicaoTarefa());
        return mv;
    }

    @PostMapping("/tarefas")
    public ModelAndView create(@Valid RequisicaoTarefa requisicao, BindingResult bindingResult){


        if (!bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("redirect:/tarefas");
            this.tarefaService.create(requisicao);
            return mv;
        }else{
            ModelAndView mv = new ModelAndView("tarefas/new");
            mv.addObject("requisicao",requisicao);
            return mv;
        }

    }

    @GetMapping("/tarefas/{id}")
    public ModelAndView show(@PathVariable UUID id){
        try{
            ModelAndView mv = new ModelAndView("tarefas/show");
            Tarefa tarefa = this.tarefaService.findById(id);
            mv.addObject("tarefa",tarefa);
            return mv;
        }catch(NullPointerException e){
            return new ModelAndView("redirect:/tarefas");
        }
    }

    @GetMapping("/tarefas/{id}/edit")
    public ModelAndView edit(@PathVariable UUID id, RequisicaoTarefa requisicao){

            try{
                Tarefa tarefa = this.tarefaService.findById(id);
                this.tarefaService.fromTarefa(tarefa,requisicao);
                ModelAndView mv = new ModelAndView("tarefas/edit");
                mv.addObject("tarefaId",tarefa.getId());
                mv.addObject("requisicao",requisicao);
                return mv;
            }catch (NullPointerException exception){
                System.out.println("ERRO");
                return new ModelAndView("redirect:tarefas/{id}");
            }


    }

    @PostMapping("/tarefas/{id}")
    public ModelAndView update(@PathVariable UUID id,@Valid RequisicaoTarefa requisicao,BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("redirect:/tarefas/{id}");
            this.tarefaService.update(id,requisicao);
            return mv;

        }else{
            ModelAndView mv = new ModelAndView("redirect:/tarefas/{id}/edit");
            mv.addObject("requisicao",requisicao);
            return mv;
        }

    }

    @GetMapping("/tarefas/{id}/delete")
    public String delete(@PathVariable UUID id){
        try{
           this.tarefaService.delete(id);
           return "redirect:/tarefas";
        }catch(Exception e){
            return  "redirect:/tarefas/" + id;
        }
    }

    @GetMapping("/tarefas/{id}/concluida")
    public String concluida(@PathVariable UUID id){
        try {
            this.tarefaService.concluida(id);
        }catch (Exception e){
            System.err.println("ERRO" + e.getMessage());
        }
        return "redirect:/tarefas/" + id;

    }
}




