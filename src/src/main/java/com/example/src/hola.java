package com.example.src;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "hola", value = "/hola")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize =  1024 * 1024 * 5 * 5)
public class hola extends HttpServlet {

        public void init() {

        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html");

            String Username = request.getParameter("username");
            String Password = request.getParameter("password");
            System.out.println("username "+Username);
            System.out.println("password "+Password);
            //System.out.println(mail+ "lista");

            Cookie cookie = new Cookie("username",Username );
            response.addCookie(cookie);

           // response.sendRedirect(request.getContextPath()+ "/form.html");

            // getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);

            PrintWriter out = response.getWriter();
            // out.println("<html><body>");
            out.println("<html><head>");
            out.println("<meta http-equiv='refresh' content= '0; URL=form.html'>");// ese 10 representa el tiempo que demore en refrescarse en segundos osea el tiempo en que demorara en llevar al otro html que especificamos
            out.println("</head><body>");
            out.println("<h1>"+"hello" +"</h1>");
            out.println("</body></html>");
        }






        public void destroy() {
        }
    }

