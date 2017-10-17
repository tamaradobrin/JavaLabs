package main.java.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class ServletUtil {

    public static void logRequestDetails(ServletContext servletContext, HttpServletRequest request){
        servletContext.log("Request made at: " + new Date().toString());
        servletContext.log("HTTP method: " + request.getMethod());
        servletContext.log("Client IP address: " + request.getRemoteAddr());
        servletContext.log("User Agent: " + request.getHeader("User-Agent"));
        String locales = "";
        for(Locale locale : Collections.list(request.getLocales())){
            locales += locale.getDisplayName();
            locales += ", ";
        }
        locales = locales.lastIndexOf(" ") > 1 ? locales.substring(0, locales.lastIndexOf(",")) : locales;
        servletContext.log("Client Language(s): " + locales);
        String params = "";
        for(String parameter : request.getParameterMap().keySet()){
            params += parameter + ", ";
        }
        params = params.lastIndexOf(" ") > 1 ? params.substring(0, params.lastIndexOf(",")) : params;
        servletContext.log("Request Parameters: " + params);
    }
}
