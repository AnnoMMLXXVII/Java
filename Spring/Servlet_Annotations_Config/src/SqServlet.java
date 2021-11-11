import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sq") // annotation similar to servlet-mapping > url-pattern
public class SqServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        int k = 0;

        Cookie cookie[] = req.getCookies();
        for (Cookie c : cookie) {
            if (c.getName().equalsIgnoreCase("k")) {
                k = Integer.parseInt(c.getValue());
            }
        }

        k = k * k;

        PrintWriter out = res.getWriter();

        out.printf("Result is: %d", k);

    }
}
