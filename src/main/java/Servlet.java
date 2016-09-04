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


//        JSONObject catchObject = new JSONObject();
        JSONObject sendObject = films.getFilmsJson(15);

//        String requestValue = "";
//
//        try {
//            if (request.getParameter("jsonData") != null) {
//                catchObject = new JSONObject(request.getParameter("jsonData"));
//
//                requestValue = catchObject.getString("color");
//                System.out.println(requestValue);
//            }
//
//            sendObject = getColor(requestValue);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        writer.println(sendObject);
        writer.flush();
    }

//    private JSONObject getColor(String requestValue) {
//        JSONObject result = new JSONObject();
//        String color = "red";
//        try {
//            switch (requestValue) {
//                case "rgb(255, 0, 0)":
//                    color = "chartreuse";
//                    break;
//                case "rgb(127, 255, 0)":
//                    color = "blue";
//                    break;
//                case "rgb(0, 0, 255)":
//                    color = "yellow";
//                    break;
//                default:
//                    color = "red";
//            }
//
//            result.append("color", color);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
