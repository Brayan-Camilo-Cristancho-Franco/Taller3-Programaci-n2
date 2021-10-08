package com.example.src;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        Optional<List<Users>> mail = AnaliseCsv.getListOfUsers();

        String templateName;

        if (mail.isPresent()) {

            request.setAttribute("mail", mail.get());
          //  templateName = "listmails.jsp";
        } else {

           // templateName = "showError.jsp";
        }

       // var dispatcher = request.getRequestDispatcher(templateName);
        //dispatcher.forward(request, response);

        String Username = request.getParameter("username");
        String Pasword = request.getParameter("pasSword");
        System.out.println("username "+Username);
        System.out.println("password "+Pasword);
        System.out.println(mail+ "lista");


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>"+"hello" +"</h1>");
        out.println("</body></html>");
    }






    public void destroy() {
    }
}