package com.HakuWei;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet_Config extends HttpServlet {

    // servlets with different values --> choose servlet_config

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        out.print("Hi <br>");

        // Will fetch param-Value in Web.xml -->
        // 	Servlet > init-param > param-name > param-value == Haku Wei

        // NOT FROM THE CONTEXT-PARAM
        ServletConfig ctx = getServletConfig();
        // can also use request
//						req.getServletContext();

        String strName = ctx.getInitParameter("name");
//		String strOccu = ctx.getInitParameter("Occupation");

        out.println(strName + "\n");
//		out.println(strOccu);


    }

}
