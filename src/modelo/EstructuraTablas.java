/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.table.DefaultTableModel;

public class EstructuraTablas {

    DefaultTableModel modeloTablaError;
    DefaultTableModel modeloTablaSimbolo;
    
    public EstructuraTablas() {
        modeloTablaError = new DefaultTableModel();
        modeloTablaSimbolo = new DefaultTableModel();

        modeloTablaSimbolo.addColumn("Token");
        modeloTablaSimbolo.addColumn("Lexema");
        modeloTablaSimbolo.addColumn("Tipo");
        this.modeloTablaSimbolo.fireTableDataChanged();

        modeloTablaError.addColumn("Token");
        modeloTablaError.addColumn("Lexema");
        modeloTablaError.addColumn("Línea");
        modeloTablaError.addColumn("Descripción");
    }

    public DefaultTableModel getModeloTablaError() {
        return modeloTablaError;
    }

    public DefaultTableModel getModeloTablaSimbolo() {
        return modeloTablaSimbolo;
    }

    public void vaciarEstructurasTablas() {  
        this.modeloTablaSimbolo.setRowCount(0);
        this.modeloTablaError.setRowCount(0);
    }

    public void agregarEnEstructurasTablas(ModeloSimbolo[] arregloSimbolos, ModeloError[] arregloErrores) {     
        for (ModeloSimbolo simbolo : arregloSimbolos) {
            this.modeloTablaSimbolo.addRow(simbolo.getAtributos());       
        }
        for (ModeloError error : arregloErrores) {
            this.modeloTablaError.addRow(error.getAtributos());
        }
    }

}
