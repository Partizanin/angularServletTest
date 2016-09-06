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
    private Films films;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        films = new Films(getServletContext().getRealPath("/data/data.json"));

        response.setContentType("text/plain; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        JSONObject catchObject = new JSONObject(request.getParameter("jsonData"));

        JSONObject sendObject = getSendObject(catchObject);

        writer.println(sendObject);
        writer.flush();
    }

    private JSONObject getSendObject(JSONObject catchObject) {

        JSONObject result = new JSONObject();

        String operation = catchObject.getString("operationCall");
        int page = catchObject.getInt("page");

        if (operation.equals("getData")) {
            result.put("films", films.getFilmsJsonByPage(page));
            result.put("pagesCount", films.getPagesCounts());
        }

        return result;
    }

}
