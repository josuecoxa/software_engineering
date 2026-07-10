package com.techflow.service;

import com.techflow.model.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TarefaServiceTest {

    private TarefaService service;

    @BeforeEach
    public void setUp() {
        service = new TarefaService();
    }

    @Test
    public void testCriarTarefa() {
        Tarefa tarefa = service.criarTarefa("Testar Sistema", "Descricao do teste", "Alta");
        assertNotNull(tarefa);
        assertEquals(1L, tarefa.getId());
        assertEquals("Testar Sistema", tarefa.getTitulo());
        assertEquals("To Do", tarefa.getStatus());
        assertEquals("Alta", tarefa.getPrioridade());
    }

    @Test
    public void testListarTodas() {
        service.criarTarefa("Tarefa 1", "Descricao 1", "Media");
        service.criarTarefa("Tarefa 2", "Descricao 2", "Baixa");
        List<Tarefa> lista = service.listarTodas();
        assertEquals(2, lista.size());
    }

    @Test
    public void testAtualizarStatus() {
        Tarefa tarefa = service.criarTarefa("Tarefa", "Descricao", "Baixa");
        boolean atualizado = service.atualizarStatus(tarefa.getId(), "In Progress");
        assertTrue(atualizado);
        List<Tarefa> lista = service.listarTodas();
        assertEquals("In Progress", lista.get(0).getStatus());
    }

    @Test
    public void testExcluirTarefa() {
        Tarefa tarefa = service.criarTarefa("Tarefa", "Descricao", "Alta");
        boolean excluido = service.excluirTarefa(tarefa.getId());
        assertTrue(excluido);
        assertTrue(service.listarTodas().isEmpty());
    }
}