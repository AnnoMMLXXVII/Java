package com.HakuWei.GetPost;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 9105822911465497611L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int i = Integer.parseInt(req.getParameter("num1"));
        int j = Integer.parseInt(req.getParameter("num2"));

        int k = i + j;
//		res.getWriter().printf("Result : %d", k);
        /// no need to print here. Will print in the SqServlet

        // Two ways to approach this
        // 1) ==> Request Dispatcher
        // 2) ==> Redirect

        // Focusing on Request Dispatcher
        // MAIN FOCUS --> NEED TO PASS THE K VALUE TO THE OTHER SERVLET (SQSERVLET) SO IT CAN BE SQUARED
        // Session management
//				-->  one way to send one servlet data to another servlet data
        req.setAttribute("k", k); // variableName and variableValue
        // ==> Look at SqServlet to see how they receive the object
        RequestDispatcher rd = req.getRequestDispatcher("sq");
        rd.forward(req, res);
        // when forwarding, need to forward the request first and then response
        // Forward the doGet params to the other servlet


    }
}
