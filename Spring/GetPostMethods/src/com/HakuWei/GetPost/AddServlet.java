package com.HakuWei.GetPost;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -5901445253580101913L;


    // Can mediate which type of request to receive. Either POST || GET
    // By using the method doPost || doGet
    // Using such method will only look to receive POST  || GET but not both


//	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {	
//		// Use Request to initialize submitted paramters
//		String i = req.getParameter("name");
//		String j = req.getParameter("year");
//		String k = i + " " + j;
//		res.getWriter().println("Result is: "+k);
//	}
    // WILL ACCEPT BOTH TYPES OF REQUESTS BUT DO GET BY DEFAULT


//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {	
//		// Use Request to initialize submitted paramters
//		String i = req.getParameter("name");
//		String j = req.getParameter("year");
//		String k = i + " " + j;
//		res.getWriter().println("Result is: "+k);
//		//URL result: http://localhost:8080/GetPostMethods/nameYear
//	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Use Request to initialize submitted paramters
        String i = req.getParameter("name");
        String j = req.getParameter("year");
        String k = i + " " + j;
        res.getWriter().println("Result is: " + k);

        // URL result: http://localhost:8080/GetPostMethods/nameYear?name=234&year=432
    }
}
