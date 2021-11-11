package com.HakuWei.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SqServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -4953546175985197986L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

//		int k = Integer.parseInt(req.getParameter("k"));
//		HttpSession session = req.getSession();
        int k = 0;
        Cookie cookies[] = req.getCookies(); //cookies are sent all at once, thus an array is needed

        for (Cookie c : cookies) {
            if (c.getName().equalsIgnoreCase("k")) {
                k = Integer.parseInt(c.getValue());
            }
        }

        // can delete session from data
//		 session.removeAttribute("k"); 
        // Must have value or attribute value first to remove it.

//		int k = (int)session.getAttribute("k"); // returns object thus mmust type cast

        PrintWriter out = res.getWriter();
        out.println("Result is: " + k);
    }

}
