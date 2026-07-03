package com.techflow;

import com.techflow.model.Tarefa;
import com.techflow.service.TarefaService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TarefaService service = new TarefaService();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("=== TechFlow Solutions - Task Manager ===");

        while (opcao != 5) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Criar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Atualizar Status da Tarefa");
            System.out.println("4. Excluir Tarefa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título da tarefa: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite a descrição da tarefa: ");
                    String descricao = scanner.nextLine();
                    Tarefa nova = service.criarTarefa(titulo, descricao);
                    System.out.println("Sucesso! Tarefa criada: " + nova);
                    break;
                case 2:
                    System.out.println("=== Lista de Tarefas ===");
                    if (service.listarTodas().isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                    } else {
                        for (Tarefa t : service.listarTodas()) {
                            System.out.println("ID: " + t.getId() + " | " + t.getTitulo() + " [" + t.getStatus() + "] - " + t.getDescricao());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID da tarefa que deseja atualizar: ");
                    Long idAtualizar = Long.parseLong(scanner.nextLine());
                    System.out.print("Digite o novo status (To Do / In Progress / Done): ");
                    String novoStatus = scanner.nextLine();
                    if (service.atualizarStatus(idAtualizar, novoStatus)) {
                        System.out.println("Status atualizado com sucesso!");
                    } else {
                        System.out.println("Tarefa com ID " + idAtualizar + " não encontrada.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID da tarefa que deseja excluir: ");
                    Long idExcluir = Long.parseLong(scanner.nextLine());
                    if (service.excluirTarefa(idExcluir)) {
                        System.out.println("Tarefa excluída com sucesso!");
                    } else {
                        System.out.println("Tarefa não encontrada.");
                    }
                    break;
                case 5:
                    System.out.println("Encerrando o sistema TechFlow Task Manager. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }
}