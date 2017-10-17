package main.java.controller;

import main.java.util.ServletUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

@WebServlet(name = "StoreInfoController", urlPatterns = {"/storeInfo"})
public class StoreInfoController extends HttpServlet {

    Map<String, String> infoMap = new TreeMap<>();
    ServletContext servletContext;

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.logRequestDetails(servletContext, req);
        String country = req.getParameter("country");
        String capital = req.getParameter("capital");
        infoMap.put(country, capital);
        servletContext.setAttribute("infoMap", infoMap);
        storeInPropertiesFile(country, capital);
        if (req.getHeader("User-Agent").equals("ServiceConsumer")) {
            ObjectOutputStream sendStream = new ObjectOutputStream(resp.getOutputStream());
            sendStream.writeObject(infoMap);
            sendStream.flush();
            sendStream.close();
        } else {
            resp.sendRedirect("/storeInfo");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtil.logRequestDetails(servletContext, req);
        Map<String, String> infoMap = (TreeMap<String, String>) getServletContext().getAttribute("infoMap");
        req.setAttribute("infoMap", infoMap);
        req.getRequestDispatcher("/showInfo.jsp").forward(req, resp);
    }

    private void storeInPropertiesFile(String country, String capital) {
        try {
            Properties properties = new Properties();
            File file = new File("T:/sem3/Lab1/src/main/resources/infoMap.properties");
            properties.load(new FileInputStream(file));
            properties.setProperty(country, capital);
            FileOutputStream fileOut = new FileOutputStream(file);
            properties.store(fileOut, "Properties file with countries and capitals");
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
