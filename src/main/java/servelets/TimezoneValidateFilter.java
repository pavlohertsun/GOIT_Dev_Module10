package servelets;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Optional;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        Optional<String> parameterOptional = Optional.ofNullable(req.getParameter("timezone"));

        if(parameterOptional.isPresent()){
            try {
                String timeZone = parameterOptional.get().replace(" ", "+");
                ZoneId.of(timeZone);
                chain.doFilter(req, res);
            } catch (DateTimeException ex) {
                res.setContentType("text/html; charset=utf-8");
                res.getWriter().write("<html><body style=\"background-color: #404040\"><div style=\"display: flex; justify-content: center;\">" +
                        "<h2 style=\"font-family: Monaco\">Invalid timezone</h2></div></body></html>");
                res.getWriter().close();
            }
        }
        else{
            chain.doFilter(req, res);
        }
    }
}
