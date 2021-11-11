package com.HakuWei.URLReWriting;

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
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int i = Integer.parseInt(req.getParameter("num1"));
        int j = Integer.parseInt(req.getParameter("num2"));

        int k = i + j;


        // NOT GOING TO USE REQUEST DISPATCHER
//		req.setAttribute("k", k);
//		RequestDispatcher rd = req.getRequestDispatcher("sq");
//		rd.forward(req, res);
        // WILL REWRITE THE URL TO PERFORM THE SERVLET TO SERVLET ACTION
        // using the response and method sendRedirect()
        res.sendRedirect("sq?k=" + k); // this will change the URL ---> REWRITING THE URL
        // ONE WAY TO PASS A VALUE TO ANOTHER SERVLET
        // due to method being  a GET... SqServlet is looking for a url specific containing parameters
        // Session Management
        // --> ways to pass from servlet to servlet
        // Sessions
        // Cookies
        // URL REWRITING


    }


}
