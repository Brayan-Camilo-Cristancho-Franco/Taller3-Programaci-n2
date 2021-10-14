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
import java.io.PrintWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

@WebServlet(name = "multiPartServlet", value = "/multiPartServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class MultipartServlet extends HttpServlet {
    private String message;
    private String UPLOAD_DIRECTORY = "uploads";
    private ArrayList<PetDto> pets = new ArrayList<>();
    private int cont = 0;


    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        Optional<List<Users>> mail = AnaliseCsv.getListOfUsers();

        String Namepet = request.getParameter("namepet");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String fecha1 = dtf.format(LocalDateTime.now()).toString();

        pets.add(new PetDto(Namepet, null, fecha1));

        if (mail.isPresent()) {

            request.setAttribute("mail", mail.get());


        } else {

        }


        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String fileName = "";
        try {
            for (Part part : request.getParts()) {
                fileName = "img" + pets.size() + ".jpg";

                part.write(uploadPath + File.separator + fileName);

                if (cont < pets.size()) {
                    pets.get(cont).setNamePhoto(fileName);
                    cont++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();

        out.println("<html><head>");
        out.println("<meta http-equiv='refresh' content= '10; URL=form.html'>");// ese 10 representa el tiempo que demore en refrescarse en segundos osea el tiempo en que demorara en llevar al otro html que especificamos
        out.println("</head><body>");
        out.println("<h1>" + "Carga de imagen exitosa" + "</h1>");
        out.println("<h1>" + "Por favor espere un momento si quiere agregar otra mascota" + "</h1>");
        out.println("</body></html>");


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("aplication/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(pets));

    }


    public void destroy() {
    }
}