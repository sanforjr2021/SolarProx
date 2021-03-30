import solarprox.data.ProxmoxHandler;
import solarprox.util.ConfigHandler;
import solarprox.util.FileLogger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", value = "/MyServlet")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>MyServlet.java:doGet(): Servlet code!</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1>This is a simple java servlet.</h1>");

            try{
                ProxmoxHandler.powerOff("100");
            } catch(IOException e){
                writer.println("<h3>Failed to run command 1</h3>");
                writer.println(e.getMessage());
            }
            try{
                ProxmoxHandler.powerOn("100");
                ProxmoxHandler.rollback("101");
            } catch(IOException e) {
                writer.println("<h3>Failed to run command 2 & 3</h3>");
                e.printStackTrace();
                writer.println(e.getMessage());
            }

            ProxmoxHandler.powerOn("100");
            writer.println("</body>");
            writer.println("</html>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
