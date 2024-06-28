package com.ed.To_doList.repositories;

import com.ed.To_doList.domains.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa,UUID> {
    List<Tarefa> findAllByCategoria_Id(UUID id);
}
