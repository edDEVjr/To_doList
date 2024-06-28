package com.ed.To_doList.controllers;

import com.ed.To_doList.domains.Categoria;
import com.ed.To_doList.domains.Tarefa;
import com.ed.To_doList.services.CategoriaService;
import com.ed.To_doList.services.CategoriaTarefaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categoriaTarefas")
public class CategoriaTarefasController {


    private CategoriaTarefaService categoriaTarefaService;
    private CategoriaService categoriaService;

    public CategoriaTarefasController(CategoriaTarefaService categoriaTarefaService, CategoriaService categoriaService) {
        this.categoriaTarefaService = categoriaTarefaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{categoriaId}/tarefas")
    public ModelAndView listarTarefas(@PathVariable UUID categoriaId){
        try{
            Categoria categoria = this.categoriaService.findById(categoriaId);
            ModelAndView mv = new ModelAndView("categoriaTarefas/index");
            mv.addObject("tarefas",this.categoriaTarefaService.findByCategoria(categoria));
            mv.addObject("categoria",categoria);
            return mv;
        }catch (Exception e){
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
            return new ModelAndView("redirect:/categorias/" + categoriaId);
        }

    }

    @GetMapping("/{categoriaId}/addTarefas")
    public ModelAndView addTarefas(@PathVariable UUID categoriaId){
        ModelAndView mv = new ModelAndView("categoriaTarefas/addTarefa");
        try{
            Categoria categoria = this.categoriaService.findById(categoriaId);
            List<Tarefa> tarefas = this.categoriaTarefaService.findAllMenosCategoria(categoria);
            mv.addObject("categoria",categoria);
            mv.addObject("tarefas",tarefas);

        }catch (Exception e){
            System.err.println("Erro ao adicionar tarefa: " + e.getMessage());
        }
        return mv;


    }

    @GetMapping("/{categoriaId}/{tarefaId}/add")
    public String addTarefaInCategoria(@PathVariable UUID categoriaId, @PathVariable UUID tarefaId){
        try{
            this.categoriaTarefaService.addTarefasInCategoria(categoriaId, tarefaId);
            System.out.println("Adicionado");
            return "redirect:/categoriaTarefas/" + categoriaId + "/tarefas";

        }catch (Exception e){
            System.err.println("Erro ao adicionar tarefa: " + e.getMessage());
            return "redirect:/categorias/" + categoriaId;
        }

    }

    @GetMapping("/{categoriaId}/{tarefaId}/delete")
    public String removeTarefaInCategoria(@PathVariable UUID categoriaId,@PathVariable UUID tarefaId){
        try{
            this.categoriaTarefaService.removeTarefaInCategoria(tarefaId,categoriaId);
        }catch (Exception e){
            System.err.println("Erro ao remover tarefa: " + e.getMessage());
        }
        return "redirect:/categoriaTarefas/" + categoriaId + "/tarefas";
    }


}
