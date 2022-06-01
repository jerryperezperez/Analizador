/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jerry
 */
public class ModeloAnalisis {

    protected Pattern patron;
    protected Matcher comparador;
    protected static EstructuraDatos estructuraDatos;
    
    public ModeloAnalisis(EstructuraDatos estructuraDatos){
        ModeloAnalisis.estructuraDatos = estructuraDatos;
    }
    public boolean detectarLexema(String lexema, String expresionRegular) {
        this.patron = Pattern.compile(expresionRegular);
        this.comparador = this.patron.matcher(lexema);
        return this.comparador.matches();
    }

}
