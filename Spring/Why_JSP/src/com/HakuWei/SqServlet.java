package com.HakuWei;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sq")
public class SqServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int k = 0;

        // using the request response to get cookies
        // receives ALL cookies thus an array
        Cookie cookies[] = req.getCookies();

        for (Cookie c : cookies) {
            if (c.getName().equalsIgnoreCase("k")) { // will only print if cookie equals "k"
                // using initialized value above, parseInt cookie values (str)
                k = Integer.parseInt(c.getValue());
            }
        }
        // response will use PrintWriter method getWriter to print out result
//		PrintWriter out = res.getWriter();

        // using out.prinln to send html tags and the .html file will parse the tags as html

//		out.println("<html><body bgcolor='cyan'>");
//		out.printf("Result : %d", k);
//		out.print("</body></html>");


    }

}
