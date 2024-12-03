package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskService {

    EmailNotificacaoService emailNotificacaoService = new EmailNotificacaoService();
    SMSNotificacaoService smsNotificacaoService = new SMSNotificacaoService();
    private ArrayList<String> taskList = new ArrayList<>();
    public String list() throws FileNotFoundException {
        if (taskList.isEmpty())  throw new FileNotFoundException( "A lista de tarefas esta vazia!" );

        String result = "";
        for(int i = 0; i < taskList.size(); i++)
            result +=  (i+1) + ". " + taskList.get(i) + "\n" ;

        emailNotificacaoService.enviarNotificacao();
        smsNotificacaoService.enviarNotificacao();

        return result;
    }

    public String add(String task) throws Exception {
        try {
            if (task.isEmpty())  {
                throw new FileNotFoundException( "A tarefa não pode ser vazia!" );
            }
            emailNotificacaoService.enviarNotificacao();
            smsNotificacaoService.enviarNotificacao();
            taskList.add(task.trim());
            return "A tarefa '" + task + "' foi adicionada!";

        } catch (NullPointerException e){
             throw new NullPointerException("A tarefa não pode ser vazia!");
        } catch (Exception e) {
            throw new Exception("Erro, valores não são válidos." +
                    "ERRO: " + e.getMessage());
        }
    }

    public String update(int index, String task) throws Exception {
        try{
            if (task.isEmpty())  {
                throw new FileNotFoundException( "A tarefa não pode ser vazia!" );
            }

            String oldTask = taskList.get(index-1);
            emailNotificacaoService.enviarNotificacao();
            smsNotificacaoService.enviarNotificacao();
            taskList.set(index-1, task.trim());
            return "A tarefa '" + oldTask + "' foi modificada para '" + task;

        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Não existe tarefa para o indice escolhido!");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor de indice inválido!");
        } catch (NullPointerException e) {
            throw new NullPointerException("A tarefa não pode ser vazia!");
        } catch (Exception e) {
            throw new Exception("Erro, valores não são válidos. " +
                    "ERRO: " + e.getMessage());
        }
    }

    public String remove(int index) throws Exception {
        try {
            String oldTask = taskList.get(index-1);
            emailNotificacaoService.enviarNotificacao();
            smsNotificacaoService.enviarNotificacao();
            taskList.remove(index-1);

            return "A tarefa '" + oldTask + "' foi removida!";

        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Não existe tarefa para o indice escolhido!");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor de indice inválido!");
        } catch (Exception e) {
            throw new Exception("Erro, valores não são válidos. " +
                    "ERRO: " + e.getMessage());
        }
    }
}
