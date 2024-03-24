package servelets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@WebServlet(value = "/time")
public class TimeServletWithParams extends HttpServlet {
    private static final String DEFAULT_TIME_ZONE = "UTC";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder resultTime = new StringBuilder();

        Optional<String> parameterOptional = Optional.ofNullable(req.getParameter("timezone"));

        String timeZone = parameterOptional.orElse(DEFAULT_TIME_ZONE).replace(" ", "+");

        String time = LocalDateTime.now(ZoneId.of(timeZone))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        resultTime.append(time).append(" ").append(timeZone);

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("<html><body style=\"background-color: #404040\"><div style=\"display: flex; justify-content: center;\">" +
                "<h2 style=\"font-family: Monaco\">" + resultTime + "</h2></div></body></html>");
        resp.getWriter().close();
    }
}
