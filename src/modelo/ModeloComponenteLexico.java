/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public abstract class ModeloComponenteLexico extends ModeloComponenteGenerico {

    private final String token;
    private final int contador;
    private static final int[] contadoresSimbolos = new int[ExpresionesRegulares.getExpresionesRegulares().length + ExpresionesRegulares.getPrefijoError().length];

    protected ModeloComponenteLexico(String token, String lexema, int numeroLinea, int iteradorExpresion) {
        super(lexema, numeroLinea);
        this.token = token;
        this.contador = ++contadoresSimbolos[iteradorExpresion];
    }

    protected ModeloComponenteLexico(ModeloComponenteLexico componenteLexico, int numeroLinea) {
        super(componenteLexico.getLexema(), numeroLinea);
        this.token = componenteLexico.getToken();
        this.contador = componenteLexico.getContador();
    }

    public String getToken() {
        return this.token;
    }

    public int getContador() {
        return this.contador;
    }

    public String getTokenCompleto() {
        return this.token.concat(String.valueOf(this.contador));
    }

    public static void reiniciarContadoresSimbolos() {
        for (int i = 0; i < contadoresSimbolos.length; i++) {
            contadoresSimbolos[i] = 0;
        }
    }

    public abstract boolean comparar(ModeloComponenteGenerico componenteGenerico);
}
