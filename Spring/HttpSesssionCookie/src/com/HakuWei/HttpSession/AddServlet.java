package com.HakuWei.HttpSession;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {


    /**
     *
     */
    private static final long serialVersionUID = -8157900053066577757L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        int i = Integer.parseInt(req.getParameter("num1"));
        int j = Integer.parseInt(req.getParameter("num2"));


        int k = (int) Math.pow(j + i, 2);
        // can use sessions to send request/response
//		HttpSession session = req.getSession();

//		session.setAttribute("k", k);

//		can use Cookies as well!
        Cookie cookies = new Cookie("k", k + "");
        res.addCookie(cookies); // add cookie to response

        // using line below, will send cookie along with response
        res.sendRedirect("sq?=" + k);


    }
}
