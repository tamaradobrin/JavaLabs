package controller;

import exception.DuplicateCountryException;
import model.Country;
import repository.CountryRepository;
import util.ServletUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "StoreInfoController", urlPatterns = {"/storeInfo"})
public class StoreInfoController extends HttpServlet {

    ServletContext servletContext;
    CountryRepository repository;
    List<Country> countries;

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
        repository = new CountryRepository();
        countries = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.logRequestDetails(servletContext, req);
        String name = req.getParameter("country");
        String capital = req.getParameter("capital");
        String continent = req.getParameter("continent");
        Country country = new Country(name, capital, continent);
        try {
            repository.storeCountryOnServer(country);
            repository.storeCountryOnSession(country, req.getSession());
        } catch (DuplicateCountryException ex) {
            req.setAttribute("error", "The country that you tried to add already exists!");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        if (req.getHeader("User-Agent").equals("ServiceConsumer")) {
            ObjectOutputStream sendStream = new ObjectOutputStream(resp.getOutputStream());
            sendStream.writeObject(countries);
            sendStream.flush();
            sendStream.close();
        } else {
            resp.sendRedirect("/storeInfo");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.logRequestDetails(servletContext, req);
        String storage = req.getParameter("storage");
        if(storage == null){
            storage = "server";
        }
        if(storage.equals("server")){
            countries = repository.getAllCountriesOnServer();
        } else if(storage.equals("session")){
            countries = repository.getAllCountriesOnSession(req.getSession());
        }
        req.setAttribute("countries", countries);
        req.getRequestDispatcher("/showInfo.jsp").forward(req, resp);
    }

    private void storeInPropertiesFile(String country, String capital) {
        try {
            Properties properties = new Properties();
            File file = new File("T:/JavaLabs/Lab1/resources/infoMap.properties");
            properties.load(new FileInputStream(file));
            properties.setProperty(country, capital);
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut, "Properties file with COUNTRIES and capitals");
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
