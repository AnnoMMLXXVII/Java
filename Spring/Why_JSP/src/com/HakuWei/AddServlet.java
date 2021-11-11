package com.HakuWei;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add") // must include the forward slash
public class AddServlet extends HttpServlet {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // parse string to integer and into an int
        int i = Integer.parseInt(req.getParameter("num1"));
        int j = Integer.parseInt(req.getParameter("num2"));
//		
        int k = j + i; // adds both parameters

        // stores sum into a cookie
//		Cookie cookie = new Cookie("k", k+"");
        // response will add the cookie object
//		res.addCookie(cookie);

        // response will re-direct the cookie to another servlet - sq (ie)
//		res.sendRedirect("sq");
    }
}
