package com.HakuWei.GetPost;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SqServlet extends HttpServlet {
    // Second servlet that will be nested inside the AddServlet
    /**
     *
     */
    private static final long serialVersionUID = 112728412058313189L;


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // finding the square of a number after submiting a request


        int k = (int) req.getAttribute("k");
        // Cast because req.getAttribute("Str") is an object of the type thus you can Type Cast
        // ex: int --> Integer = (Type Cast) => int || double --> Double = (Type Cast) => double
//		k = k*;
//		k = (int)Math.random();
        for (int i = 0; i < 1000; i++) {
            res.getWriter().println(i + " : " + k * i);
        }
    }

}
