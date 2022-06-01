/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import modelo.*;
import vista.Interfaz;

public class Controlador implements ActionListener {

    Interfaz interfaz;
    AnalisisLexico analisisLexico;
    AnalisisSintactico analisisSintaxis;
    AnalisisSemantico analisisSemantico;
    EstructuraTablas estructuraTablas;
    EstructuraDatos estructuraDatos;

    Controlador(Interfaz interfaz, EstructuraTablas estructuraTablas, EstructuraDatos estructuraDatos) {
        this.interfaz = interfaz;
        this.estructuraTablas = estructuraTablas;
        this.estructuraDatos = estructuraDatos;
        this.analisisSintaxis = new AnalisisSintactico(this.estructuraDatos);
        this.analisisSemantico = new AnalisisSemantico(this.estructuraDatos);
        this.analisisLexico = new AnalisisLexico(this.estructuraDatos);
    }

    public void iniciar() {
        this.interfaz.botonCompilar.addActionListener(this);
        this.interfaz.botonBorrarTodo.addActionListener(this);
        this.interfaz.botonGenerarArchivo.addActionListener(this);
        this.interfaz.setTitle("Compilador 3.0 Version");
        this.interfaz.pack();
        this.interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.interfaz.establecerLookAndFeel();
        this.interfaz.setVisible(true);
        this.interfaz.setLocation(500, 500);
        this.interfaz.tablaSimbolos.setModel(this.estructuraTablas.getModeloTablaSimbolo());
        this.interfaz.tablaErrores.setModel(estructuraTablas.getModeloTablaError());
        this.agregarTablasEnVista();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.interfaz.botonCompilar == e.getSource()) {
            if (!this.interfaz.textArea.getText().isEmpty()) {
                this.compilar();
            }
        }
        if (this.interfaz.botonBorrarTodo.equals(e.getSource())) {
            this.borrarTodo();
        }
        if (this.interfaz.botonGenerarArchivo.equals(e.getSource())) {
            Archivo.crearArchivo(this.estructuraDatos.getTextoTokens(), JOptionPane.showInputDialog("Ingrese el nombre del archivo"));
        }
    }

    private void compilar() {
        this.reiniciarDatos();
        this.comenzarAnalisisLexico();
        if (this.estructuraDatos.getArregloErrores().length==0) {
            this.comenzarAnalisisSintactico();
        }
        System.out.println("ssssss");
        if(this.estructuraDatos.getArregloErrores().length==0){
            System.out.println("COMIENZA SEMANTICO");
            this.comenzarAnalisisSemantico();
        }
        this.estructuraTablas.agregarEnEstructurasTablas(this.estructuraDatos.getArregloSimbolos(), this.estructuraDatos.getArregloErrores());
        this.imprimirTokens();
    }

    private void comenzarAnalisisLexico() {
        this.analisisLexico.separarLineas(this.interfaz.textArea.getText());
    }

    private void comenzarAnalisisSintactico() {
        this.analisisSintaxis.detectarInicioSentencia();
    }

    private void comenzarAnalisisSemantico() {
        this.analisisSemantico.definirTipoDato();
        this.analisisSemantico.validarIncompatibilidadTipos();
    }

    private void agregarTablasEnVista() {
        this.interfaz.tablaSimbolos.setModel(this.estructuraTablas.getModeloTablaSimbolo());
        this.interfaz.tablaErrores.setModel(this.estructuraTablas.getModeloTablaError());
    }

    private void imprimirTokens() {
        this.estructuraDatos.prepararTextoTokens();
        this.interfaz.textAreaTokens.setText(this.estructuraDatos.getTextoTokens());
    }

    private void borrarTodo() {
        this.reiniciarDatos();
        this.interfaz.textArea.setText("");
        this.interfaz.textAreaTokens.setText("");
    }

    private void reiniciarDatos() {
        this.estructuraDatos.vaciarDatos();
        this.estructuraTablas.vaciarEstructurasTablas();
        ModeloComponenteLexico.reiniciarContadoresSimbolos();
    }

}
