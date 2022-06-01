/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jerry
 */
public class Nodo {

    private Nodo[] arregloNodosApuntados;

    private final String[] valores;
    public final String nombre;

    public Nodo(String nombre, String... valores) {
        this.nombre = nombre;
        this.valores = valores;
    }


    public void apunta(Nodo... nodosApuntados) {
        
        this.arregloNodosApuntados = nodosApuntados;
    }

    public int getNodos() {
        return this.arregloNodosApuntados.length;
    }

    public String[] getValores() {
        return this.valores;
    }

    public Nodo encuentraNodoSiguiente(ModeloComponenteLexico componenteLexico) {
        for (Nodo nodoApuntado : this.arregloNodosApuntados) {
            for (String valor : nodoApuntado.getValores()) {
                System.out.println("valor:"+ valor);
                if (componenteLexico.getToken().equals(valor) || componenteLexico.getLexema().equals(valor)) {
                    return nodoApuntado;
                }
            }
        }

            System.out.println("NO ENCONTRÓ NODO SIGUIENTE");
            return null;
        
    }

}
