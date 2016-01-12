package com.teslenko.currcomp.controllers;

import com.teslenko.currcomp.dao.db.ExchangesJdbcDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("button");
        ExchangesJdbcDao dao = new ExchangesJdbcDao();
        switch (action) {
            case "Add":
                dao.insertRecord(
                        request.getParameter("domain"),
                        request.getParameter("name"),
                        request.getParameter("partner-url"),
                        request.getParameter("rates-url"),
                        request.getParameter("status"));
                break;
            case "Confirm":
                dao.updateRecord(
                        request.getParameter("domain"),
                        request.getParameter("name"),
                        request.getParameter("partner-url"),
                        request.getParameter("rates-url"),
                        request.getParameter("status"),
                        Integer.valueOf(request.getParameter("id")));
                break;
            case "Delete":
                dao.deleteRecord(Integer.valueOf(request.getParameter("id")));
                break;
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin/admin.jsp");
        dispatcher.forward(request, response);
    }
}
