package com.ed.To_doList.services;

import com.ed.To_doList.domains.Categoria;
import com.ed.To_doList.dtos.RequisicaoCategoria;
import com.ed.To_doList.repositories.CategoriaRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.UUID;


@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;


    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;

    }

    public void create(RequisicaoCategoria requisicao){
        Categoria newCategoria = new Categoria();
        newCategoria.setNome(requisicao.getNome());
        newCategoria.setDescricao(requisicao.getDescricao());
        this.categoriaRepository.save(newCategoria);

    }

    public void update(UUID id, RequisicaoCategoria requisicao){
        Optional<Categoria> optional = this.categoriaRepository.findById(id);
        if(optional.isPresent()){
            Categoria categoria = optional.get();
            categoria.setNome(requisicao.getNome());
            categoria.setDescricao(requisicao.getDescricao());
            this.categoriaRepository.save(categoria);
        }else{
            System.out.println("**********ID INEXISTENTE**********");
        }
    }

    public void delete(UUID id){
        Optional<Categoria> optional = this.categoriaRepository.findById(id);
        if(optional.isPresent()){
            Categoria categoria = optional.get();
            this.categoriaRepository.delete(categoria);
        }else{
            System.out.println("**********ID INEXISTENTE**********");

        }
    }

    public Categoria findById(UUID id){
        Optional<Categoria> optional = this.categoriaRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Categoria> findAll(){
        return this.categoriaRepository.findAll();
    }

    public void fromCategoria(Categoria categoria,RequisicaoCategoria requisicao){
        requisicao.setNome(categoria.getNome());
        requisicao.setDescricao(categoria.getDescricao());
    }




}
