package com.example.src;

import java.time.format.DateTimeFormatter;

/*
Clase Dto de mascotas donde estan los parametros de nombre, nombre de la foto y la fecha en que fue subida imagen.
 */
public class PetDto {

    private String name;
    private String namePhoto;
    private String date;


    public PetDto(String name, String namePhoto, String date) {
        this.name = name;
        this.namePhoto = namePhoto;
        this.date = date;
    }

    /*
 metodo que se sobrescribe de toString para retornar los valores de los objetos
   */
    @Override
    public String toString() {
        return "PetDto{" +
                "name='" + name + '\'' +
                ", namePhoto='" + namePhoto + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePhoto() {
        return namePhoto;
    }

    public void setNamePhoto(String namePhoto) {
        this.namePhoto = namePhoto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
