package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InputController", urlPatterns = {"/input"})
public class InputController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("continents", getListOfContinents());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private List<String> getListOfContinents(){
        List<String> continents = new ArrayList<>();
        continents.add("Europe");
        continents.add("Africa");
        continents.add("Asia");
        continents.add("Australia");
        continents.add("Antarctica");
        continents.add("North America");
        continents.add("South America");
        return continents;
    }
}