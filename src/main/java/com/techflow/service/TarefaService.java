package com.techflow.service;

import com.techflow.model.Tarefa;
import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private Long proximoId = 1L;

    public Tarefa criarTarefa(String titulo, String descricao) {
        Tarefa novaTarefa = new Tarefa(proximoId, titulo, descricao, "To Do");
        listaTarefas.add(novaTarefa);
        proximoId++;
        return novaTarefa;
    }

    public List<Tarefa> listarTodas() {
        return new ArrayList<>(listaTarefas);
    }

    public boolean atualizarStatus(Long id, String novoStatus) {
        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getId().equals(id)) {
                tarefa.setStatus(novoStatus);
                return true;
            }
        }
        return false;
    }

    public boolean excluirTarefa(Long id) {
        return listaTarefas.removeIf(tarefa -> tarefa.getId().equals(id));
    }
}