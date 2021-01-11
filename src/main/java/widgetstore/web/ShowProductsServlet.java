package widgetstore.web;

import desserts.DessertDAOImpl;
import desserts.DessertDTO;
import desserts.GenericDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShowProductsServlet extends HttpServlet {

    GenericDAO<DessertDTO> dessertDTO;

    public ShowProductsServlet() {
        dessertDTO = new DessertDAOImpl();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<form action='' method='POST'>");
        out.println("<label>Enter Dessert Name: <input type='text' name='dessert-name'></input></label>");
        out.println("<input type='submit'>Create Dessert</input>");
        out.println("</form>");

        out.println("<h2>Desserts</h2>");
        for (DessertDTO dessert: dessertDTO.getDesserts()) {
            out.println("<p>" + dessert.getName() + "</p>");
        }
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("dessert-name");
        dessertDTO.create(new DessertDTO(
                name,
                true
        ));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Dessert was created");
    }
}
