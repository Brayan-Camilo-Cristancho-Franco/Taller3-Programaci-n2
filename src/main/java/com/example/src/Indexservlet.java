package com.example.src;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;


@WebServlet(name = "Indexservlet", value = "/Indexservlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Indexservlet extends HttpServlet {
    private Users u;
    ArrayList<Users> user = new ArrayList<Users>();
    String rol;


    public void init() {

    }
    /*
     * metodo doPost encargado de traer los valores de username y password, ademas de añade las 2 cookies
     *
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        response.setContentType("text/html");

        String Username = request.getParameter("username");
        String Password = request.getParameter("password");
        System.out.println("username " + Username);
        System.out.println("password " + Password);

        user.add(new Users("funcionario@gmail.com", "funcionario123", "funcionario"));
        user.add(new Users("propietario@gmail.com", "propietario123", "propietario"));
        System.out.println(Arrays.toString(new ArrayList[]{user}) + " user");


        if (user.get(1).getMail().equals(Username)) {

            rol = "propietario";
            Cookie cookieUser = new Cookie("username", Username);
            cookieUser.setMaxAge(3600);
            response.addCookie(cookieUser);

            Cookie cookiePasswoord = new Cookie("password", Password);
            cookiePasswoord.setMaxAge(3600);
            response.addCookie(cookiePasswoord);


            Cookie cookieRol = new Cookie("rol", rol);
            cookieRol.setMaxAge(3600);
            response.addCookie(cookieRol);

            response.sendRedirect(request.getContextPath() + "/form.html");


        } else if (user.get(0).getMail().equals(Username)) {
            rol = "funcionario";
            Cookie cookieUser = new Cookie("username", Username);
            cookieUser.setMaxAge(3600);
            response.addCookie(cookieUser);

            Cookie cookiePasswoord = new Cookie("password", Password);
            cookiePasswoord.setMaxAge(3600);
            response.addCookie(cookiePasswoord);

            Cookie cookieRol = new Cookie("rol", rol);
            cookieRol.setMaxAge(3600);
            response.addCookie(cookieRol);

        }
        response.sendRedirect(request.getContextPath() + "/Funcionario.html");
    }


    public void destroy() {
    }
}

