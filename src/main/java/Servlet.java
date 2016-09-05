import org.json.JSONObject;
import utils.Films;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with Intellij IDEA.
 * Project name: angularSevletTest.
 * Date: 03.09.2016.
 * Time: 18:23.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
@WebServlet(name = "Servlet", urlPatterns = "/Servlet")
public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Films films = new Films();
        response.setContentType("text/plain; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        JSONObject sendObject = films.getFilmsJsonByPage(Integer.parseInt(request.getParameter("page")));

        writer.println(sendObject);
        writer.flush();
    }

}
