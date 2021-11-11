package com.HakuWei;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -4276433372578077432L;

    // need to use this method name
    // HOW does this servlet class handle the submit request from the HTML files
    // Syntax
    // Request Object
    // Response Object
    // Request and Reponse
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // getParameter returns String
        // cannot perform mathematical operations on String,
        // thus use Ingeter.parseInt(); --> returns Integer
        int i = Integer.parseInt(req.getParameter("num1"));
        int j = Integer.parseInt(req.getParameter("num2"));

        int k = i + j;

        // Will not work just yet! ==> MUST DEFINE PAGE in the form action = 'add'
        // Must deifne the flow of the pages
        // to configure... all flow will be configured in teh web.xml page in WebContent > WEB-INF > web.xml
        // If ran
        // Error will occur
        // Look at URL
        // path/add?num1=#&num2=# ==> Query String
        // Query String ==> Allows for info to be sent from client to Server
        PrintWriter out = res.getWriter();

        out.println("Result is " + k);
    }

}
