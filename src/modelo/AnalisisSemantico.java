/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class AnalisisSemantico {

    private final EstructuraDatos estructuraDatos;
    private String tipoDatoActual;
    int ab;
    public AnalisisSemantico(EstructuraDatos estructuraDatos) {
        this.estructuraDatos = estructuraDatos;
    }

    public void definirTipoDato() {
        for (ModeloSimbolo simbolo : this.estructuraDatos.getArregloSimbolosRepetidos()) {
            if (simbolo.getToken().equals("TDA")) {
                this.tipoDatoActual = simbolo.getLexema();
            } else if (simbolo.getToken().equals("IDE")) {
                if (simbolo.getTipo() == null) {
                    System.out.println("ASIGNA TIPO DE ");
                    simbolo.setTipo(tipoDatoActual);
                } else {
                    System.out.println("DEBE ENTRAAAR");
               
                      //  this.estructuraDatos.agregarComponenteLexico(new ModeloError("ERRSE", simbolo, 12, "Variable ya definida"));
                    
                }
            } else if (simbolo.getLexema().equals(";")) {
                this.tipoDatoActual = null;
            }
        }
    }

    public void validarIncompatibilidadTipos() {
        this.tipoDatoActual =  null;
        for (ModeloSimbolo simbolo : this.estructuraDatos.getArregloSimbolos()) {
            
            if(simbolo.getToken().equals("IDE")){
                if(simbolo.getTipo()==null){
                    this.estructuraDatos.agregarComponenteLexico(new ModeloError("ERRSE", simbolo, 12, "Variable no definida"));
                }else if(this.tipoDatoActual == null){
                     this.tipoDatoActual = simbolo.getTipo();
                }else if(!simbolo.getTipo().equals(this.tipoDatoActual)){
                     this.estructuraDatos.agregarComponenteLexico(new ModeloError("ERRSE", simbolo, 12, "Incompatibilidad de tipos de datos"));
                }
            }          
        }
    }
}
