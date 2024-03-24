package servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

//@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    private static final String TIME_ZONE = "UTC";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String time = LocalDateTime.now(ZoneId.of(TIME_ZONE))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        StringBuilder resultTime = new StringBuilder();
        resultTime.append(time).append(" ").append(TIME_ZONE);

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("<html><body style=\"background-color: #404040\"><div style=\"display: flex; justify-content: center;\"><h2 style=\"font-family: Monaco\">" + resultTime + "</h2></div></body></html>");
        resp.getWriter().close();
    }
}
