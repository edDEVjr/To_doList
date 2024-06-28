package com.ed.To_doList.controllers;

import com.ed.To_doList.domains.Categoria;
import com.ed.To_doList.dtos.RequisicaoCategoria;
import com.ed.To_doList.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller()
public class CategoriaController {
    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/categorias")
    public ModelAndView index(){
        List<Categoria> categorias = categoriaService.findAll();
        ModelAndView mv = new ModelAndView("categorias/index");
        mv.addObject("categorias",categorias);
        return mv;
    }

    @GetMapping("/categorias/new")
    public ModelAndView nnew(){
        ModelAndView mv = new ModelAndView("categorias/new");
        mv.addObject("requisicao",new RequisicaoCategoria());
        return mv;

    }

    @PostMapping("/categorias")
    public ModelAndView create(@Valid RequisicaoCategoria requisicao,BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("redirect:/categorias");
            this.categoriaService.create(requisicao);
            return mv;
        }else{
            ModelAndView mv = new ModelAndView("categorias/new");
            mv.addObject("requisicao",requisicao);
            return mv;
        }

    }

    @GetMapping("/categorias/{id}")
    public ModelAndView show(@PathVariable UUID id){
        try{
            ModelAndView mv = new ModelAndView("categorias/show");
            Categoria categoria  = this.categoriaService.findById(id);
            mv.addObject("categoria",categoria);
            return mv;
        }catch(Exception e){
            return new ModelAndView("redirect:/categorias");
        }
    }

    @GetMapping("/categorias/{id}/edit")
    public ModelAndView edit(@PathVariable UUID id, RequisicaoCategoria requisicao){

        try{
            Categoria categoria = this.categoriaService.findById(id);
            this.categoriaService.fromCategoria(categoria,requisicao);
            ModelAndView mv = new ModelAndView("categorias/edit");
            mv.addObject("categoriaId",categoria.getId());
            mv.addObject("requisicao",requisicao);
            return mv;
        }catch (Exception exception){
            System.out.println("ERRO");
            return new ModelAndView("redirect:categorias/{id}");
        }


    }

    @PostMapping("/categorias/{id}")
    public ModelAndView update(@PathVariable UUID id,@Valid RequisicaoCategoria requisicao,BindingResult bindingResult){
        if (!bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("redirect:/categorias/{id}");
            this.categoriaService.update(id,requisicao);
            return mv;

        }else{
            ModelAndView mv = new ModelAndView("redirect:/categorias/{id}/edit");
            mv.addObject("requisicao",requisicao);
            return mv;
        }

    }

    @GetMapping("/categorias/{id}/delete")
    public String delete(@PathVariable UUID id){
        try{
            this.categoriaService.delete(id);
            return "redirect:/categorias";
        }catch(Exception e){
            return  "redirect:/categorias/{id}";
        }
    }



}
