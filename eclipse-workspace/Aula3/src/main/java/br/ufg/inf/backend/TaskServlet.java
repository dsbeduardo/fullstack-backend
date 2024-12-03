package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {

    TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try{
            response.getWriter().println(taskService.list());
        } catch (FileNotFoundException e) {
            response.getWriter().println( e.getMessage() );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            String task = request.getParameter("task");

            response.getWriter().println(taskService.add(task));
        } catch (Exception e) {
            response.getWriter().println( e.getMessage() );
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            String task = request.getParameter("task");
            int index = Integer.parseInt(request.getParameter("index"));

            response.getWriter().println(taskService.update(index, task));
        } catch (Exception e) {
            response.getWriter().println( e.getMessage() );
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(request.getParameter("index"));

            response.getWriter().println(taskService.remove(index));
        } catch (Exception e) {
            response.getWriter().println( e.getMessage() );
        }
    }
}
