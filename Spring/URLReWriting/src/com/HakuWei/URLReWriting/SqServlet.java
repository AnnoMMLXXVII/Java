package com.HakuWei.URLReWriting;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SqServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

//		int k = (int) req.getAttribute("k"); // null pointer
        int k = Integer.parseInt(req.getParameter("k")); // will make a null pointer

        // FIX BY RE-WRITING URL FROM THE SENDER SERVLET

        res.getWriter().printf("k = %d; f(k) = k*k; f(k) = %d", k, k * k);

//		System.out.println("Sq called"); // test purpose to see if SQ is called


    }


}
