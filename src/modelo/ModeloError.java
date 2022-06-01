/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class ModeloError extends ModeloComponenteLexico {
    private final String descripcion;
    String tipo;

    public ModeloError(String token, ModeloComponenteGenerico componenteGenerico, int contador, String descripcion) {
        super(token, componenteGenerico.getLexema(), componenteGenerico.getNumeroLinea(), contador);
        this.descripcion = descripcion;       
    }

    public Object[] getAtributos() {
        return new Object[]{this.getTokenCompleto(), this.getLexema(), this.getNumeroLinea(), this.descripcion};
    }

    public String getDescripcion() {
        return this.descripcion;
    }  

    @Override
    public boolean comparar(ModeloComponenteGenerico componenteGenerico) {
        return (this.getLexema().equals(componenteGenerico.getLexema()) && this.getNumeroLinea() == (componenteGenerico.getNumeroLinea()));
    }
   
}
