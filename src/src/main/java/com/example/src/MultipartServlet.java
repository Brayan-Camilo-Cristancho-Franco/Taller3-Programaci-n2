package com.example.src;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "multiPartServlet", value = "/multiPartServlet")
@MultipartConfig (fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize =  1024 * 1024 * 5 * 5)
public class MultipartServlet extends HttpServlet {
    private String message;
    private String UPLOAD_DIRECTORY = "uploads";


    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        Optional<List<Users>> mail = AnaliseCsv.getListOfUsers();




        if (mail.isPresent()) {

            request.setAttribute("mail", mail.get());

          //  templateName = "listmails.jsp";
        } else {

           // templateName = "showError.jsp";
        }

        /*String templateName;
        var dispatcher = request.getRequestDispatcher(templateName);
        dispatcher.forward(request, response);*/

        /*String Username = request.getParameter("username");
        String Password = request.getParameter("password");
        System.out.println("username "+Username);
        System.out.println("password "+Password);
        //System.out.println(mail+ "lista");

        Cookie cookie = new Cookie("username",Username );
        response.addCookie(cookie);*/

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String fileName = "";
        try {
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //response.sendRedirect(request.getContextPath()+ "/form.html");

       // getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);


    }






    public void destroy() {
    }
}