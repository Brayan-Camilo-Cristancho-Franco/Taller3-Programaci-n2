package com.example.src;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            response.sendRedirect(request.getContextPath() + "/form.html");


        } else if (user.get(0).getMail().equals(Username)) {
            rol = "funcionario";
            response.sendRedirect(request.getContextPath() + "/Funcionario.html");

        }


        response.sendRedirect(request.getContextPath() + "/form.html");

        Cookie cookieUser = new Cookie("username", Username);

        response.addCookie(cookieUser);

        Cookie cookieRol = new Cookie("rol", rol);

        response.addCookie(cookieRol);


    }


    public void destroy() {
    }
}

