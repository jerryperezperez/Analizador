/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class ModeloComponenteGenerico {
    private final String lexema;
    private final int numeroLinea;
   public ModeloComponenteGenerico (String lexema, int numeroLinea){
       this.lexema = lexema;
       this.numeroLinea = numeroLinea;
   }
   
   public String getLexema(){
       return this.lexema;
   }
   
   public int getNumeroLinea(){
       return this.numeroLinea;
   }
   
   
   
   
   
  
}
