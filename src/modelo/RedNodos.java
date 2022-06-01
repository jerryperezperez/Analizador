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
public class RedNodos {

    Nodo nodoRaiz;
    Nodo nodoFinal;
    Nodo nodoPuntoYcoma;
    Nodo nodoDato;
    Nodo nodoDato2;
    Nodo nodoSeparadorCierre;
    Nodo nodoOperador;
    Nodo nodoSeparadorApertura;
    Nodo nodoTDA;
    Nodo nodoIDE;
    Nodo nodoAsignacion;
    Nodo nodoComa;
    Nodo nodoActual;

    public RedNodos() {
        nodoRaiz = new Nodo("nodoRaiz");
        nodoFinal = new Nodo("nodoFinal");
        nodoPuntoYcoma = new Nodo("NodoPuntoYcoma", ";");
        nodoTDA = new Nodo("nodoTDA", ExpresionesRegulares.expresionTDA);
        nodoAsignacion = new Nodo("nodoAsignacion", ExpresionesRegulares.expresionAsignacion);
        nodoComa = new Nodo("nodoComa", ExpresionesRegulares.expresionComa);
        nodoIDE = new Nodo("nodoIDE", ExpresionesRegulares.expresionIDE);
        nodoDato = new Nodo("nodoDato", ExpresionesRegulares.expresionDatos);
        nodoSeparadorCierre = new Nodo("nodoSepradorCierre", ExpresionesRegulares.expresionSeparadorCierre);
        nodoDato2 = new Nodo("nodoDato2", ExpresionesRegulares.expresionDatos);
        nodoOperador = new Nodo("nodoOperador", ExpresionesRegulares.expresionOperadores);
        nodoSeparadorApertura = new Nodo("nodoSeparadorApertura", ExpresionesRegulares.expresionSeparadorApertura);
        
        Nodo arreglo3[] = {nodoPuntoYcoma, nodoOperador, nodoSeparadorCierre};
        Nodo arreglo2[] = {nodoSeparadorApertura, nodoDato};

        
        Nodo arregloInicial[] = {nodoTDA, nodoIDE};
        nodoRaiz.apunta(arregloInicial);
        nodoTDA.apunta(nodoIDE);
        nodoIDE.apunta(nodoComa, nodoPuntoYcoma, nodoAsignacion);
        nodoDato.apunta(nodoOperador, nodoPuntoYcoma, nodoSeparadorCierre);
        nodoAsignacion.apunta(nodoIDE, nodoSeparadorApertura, nodoDato);
        nodoSeparadorApertura.apunta(nodoDato, nodoSeparadorApertura);
        nodoComa.apunta(nodoIDE);
        nodoOperador.apunta(arreglo2);
        nodoSeparadorCierre.apunta(arreglo3);
        nodoPuntoYcoma.apunta(nodoTDA, nodoIDE);
        this.nodoActual = this.nodoRaiz;
    
    }
}
