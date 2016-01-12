package com.teslenko.currcomp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Roman on 16.11.2015.
 */
public class RatesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher;
        if (request.getParameter("search-type") != null) {
            switch (request.getParameter("search-type")) {
                case "direct":
                    dispatcher = request.getRequestDispatcher("pages/user/rates-direct.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "chains":
                    dispatcher = request.getRequestDispatcher("pages/user/rates-chain.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } else {
            dispatcher = request.getRequestDispatcher("pages/user/rates.jsp");
            dispatcher.forward(request, response);
        }
    }
}
