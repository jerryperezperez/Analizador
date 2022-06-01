/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.io.FileNotFoundException;

import java.io.PrintWriter;

public final class Archivo {
    static private PrintWriter pw;
    static private String ruta;

    public static void crearArchivo(String contenido, String nombreArchivo) {
        try {
            ruta = System.getProperty("user.dir") + "\\" + nombreArchivo + ".txt";
            pw = new PrintWriter(ruta);
            pw.write(contenido);
            pw.close();
        } catch (FileNotFoundException e) {
         //debe ir algo
        }
    }
}
