/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class ModeloSimbolo extends ModeloComponenteLexico {

    private String tipo;

    public ModeloSimbolo(String token, ModeloComponenteGenerico componenteGenerico, int posicionContador) {
        super(token, componenteGenerico.getLexema(), componenteGenerico.getNumeroLinea(), posicionContador);

    }
    
    public ModeloSimbolo(ModeloComponenteLexico componenteLexico, int numeroLinea){
        super(componenteLexico, numeroLinea);
    }

    public String getTipo() {
        return this.tipo;
    }

    public Object[] getAtributos() {       
        return new Object[]{this.getTokenCompleto(), this.getLexema(), this.getTipo()};
    }

    public boolean comparar(ModeloComponenteGenerico componenteGenerico) {
        return (this.getLexema().equals(componenteGenerico.getLexema()));
        
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;

    }
    
}
