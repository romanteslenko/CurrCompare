package com.teslenko.currcomp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Roman on 15.11.2015.
 */
public class ArbitrageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("currency") != null) {
            response.setContentType("text/html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/arbitrage-currency.jsp");
            dispatcher.forward(request, response);
        } else {
            response.setContentType("text/html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/user/arbitrage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
