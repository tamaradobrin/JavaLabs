package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("pass");
        if(username.equals("tami") && password.equals("pass_word")){
            User loggedInUser = new User(username, password);
            req.getSession().setAttribute("user", loggedInUser);
            resp.sendRedirect("/input");
        } else {
            req.setAttribute("error", "Invalid username or password!");
            req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
        }
    }
}
