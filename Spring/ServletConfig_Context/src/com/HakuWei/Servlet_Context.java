package com.HakuWei;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet_Context extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * ServletConfig & ServletContext
     *
     * Used to grab initial values of the application/servlet
     *
     * specify username and password for example
     *
     */

    // IF multiple servlets exist with the same value, choose servlet context

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();

        out.print("Hi <br>");

        // will fetch value from Web.XML
        // context-param > param-name > param-value

        // NOT FROM THE INIT-PARAM -VALUE
        ServletContext ctx = getServletContext();
        // can also use request
//						req.getServletContext();

        String strName = ctx.getInitParameter("Name");
        String strOccu = ctx.getInitParameter("Occupation");

        out.println(strName + "\n");
        out.println(strOccu);


        // Output:

        // Hi Haku
        // Software Engineer


    }

}
