package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebFilter("/DecoratorFilter")
public class DecoratorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, wrapper);

        String content = wrapper.toString();

        StringWriter sw = new StringWriter();
        sw.write(content);
        sw.write("<div class=\"navbar navbar-fixed-bottom footer\">@Copyright Infoiasi 2017</footer></div>");

        PrintWriter out = servletResponse.getWriter();
        out.write(sw.toString());
    }
}
