package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    ArrayList<String> taskList = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (taskList.isEmpty()) response.getWriter().println("A lista de tarefas esta vazia!");
      for(int i = 0; i < taskList.size(); i++)
        response.getWriter().println((i+1) + taskList.get(i));
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
        String task = request.getParameter("task").trim();
        if (task.isEmpty())  {
          response.getWriter().println( "A tarefa não pode ser vazia!" );
          return;
        }
        taskList.add(task);
        response.getWriter().println( "A tarefa '" + task + "' foi adicionada!" );
      } catch (NullPointerException e){
          response.getWriter().println("A tarefa não pode estar vazia");
      } catch (Exception e) {
          response.getWriter().println("Erro, valores não são válidos.");
          response.getWriter().println("ERRO: " + e.getMessage());
      }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try{
        String task = request.getParameter("task").trim();
        if (task.isEmpty()) {
          response.getWriter().println( "A tarefa não pode ser vazia!" );
          return;
        }
        int index = Integer.parseInt(request.getParameter("index"));
        String oldTask = taskList.get(index-1);
        taskList.set(index-1, task);
        response.getWriter().println( "A tarefa '" + oldTask + "' foi modificada para '" + task );
      } catch (IndexOutOfBoundsException e) {
          response.getWriter().println("Não existe tarefa para o indice informado!");
      } catch (NumberFormatException e) {
          response.getWriter().println("Valor de indice inválido!");
      } catch (NullPointerException e) {
          response.getWriter().println("A tarefa não pode ser vazia!");
      } catch (Exception e) {
          response.getWriter().println("Erro, valores não são válidos.");
          response.getWriter().println("ERRO: " + e.getMessage());
      }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
        int index = Integer.parseInt(request.getParameter("index"));
        String oldTask = taskList.get(index-1);
        taskList.remove(index-1);
        response.getWriter().println( "A tarefa '" + oldTask + "' foi removida!" );
      } catch (IndexOutOfBoundsException e) {
          response.getWriter().println("Não existe tarefa para o indice informado!");
      } catch (NumberFormatException e) {
          response.getWriter().println("Valor de indice inválido!");
      } catch (Exception e) {
          response.getWriter().println("Erro, valores não são válidos.");
          response.getWriter().println("ERRO: " + e.getMessage());
      }
    }
}
