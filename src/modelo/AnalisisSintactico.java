package modelo;

public class AnalisisSintactico {

    private String finErrorSintactico = "";
    private final EstructuraDatos estructuraDatos;
    private int diferenciaDeParentesis;
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
    ModeloComponenteLexico ultimoSeparador;

    Nodo nodoActual;

    public AnalisisSintactico(EstructuraDatos estructuraDatos) {

        this.diferenciaDeParentesis = 0;
        this.estructuraDatos = estructuraDatos;
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
        nodoIDE.apunta(nodoComa, nodoPuntoYcoma, nodoAsignacion, nodoOperador);
        nodoDato.apunta(nodoOperador, nodoPuntoYcoma, nodoSeparadorCierre);
        nodoAsignacion.apunta(nodoIDE, nodoSeparadorApertura, nodoDato);
        nodoSeparadorApertura.apunta(nodoDato, nodoSeparadorApertura);
        nodoComa.apunta(nodoIDE);
        nodoOperador.apunta(arreglo2);
        nodoSeparadorCierre.apunta(arreglo3);
        nodoPuntoYcoma.apunta(nodoTDA, nodoIDE);
        this.nodoActual = this.nodoRaiz;
    }

    public void detectarInicioSentencia() {
        this.estructuraDatos.definirArregloErroresSinSIntacticos();
        for (int iterador = 0; iterador < this.estructuraDatos.getArregloErroresSinSintacticos().length; iterador++) {
            System.out.println("fff" + this.estructuraDatos.getArregloErroresSinSintacticos().length);
            System.out.println("NOD ACTUAL:" + this.nodoActual.nombre);
            System.out.println("PALABRA:" + this.estructuraDatos.getArregloErroresSinSintacticos()[iterador].getLexema());
            this.nodoActual = this.nodoActual.encuentraNodoSiguiente(this.estructuraDatos.getArregloErroresSinSintacticos()[iterador]);
            if (this.nodoActual != null) {
                if (this.nodoActual.equals(nodoSeparadorApertura) || this.nodoActual.equals(nodoSeparadorCierre)) {
                    this.ultimoSeparador = this.estructuraDatos.getArregloErroresSinSintacticos()[iterador];
                    if (!this.isSeparadorCierreValido()) {
                        iterador = this.procesarErrorSintactico(this.estructuraDatos.getArregloErroresSinSintacticos()[iterador], iterador);
                    } else {
                        this.contabilizarCantidadParentesis(iterador);
                    }
                }
            } else {
                iterador = this.procesarErrorSintactico(this.estructuraDatos.getArregloErroresSinSintacticos()[iterador], iterador);
            }
            if (this.nodoActual.equals(nodoPuntoYcoma)) {
                this.nodoActual = nodoRaiz;
            }
        }
        System.out.println(nodoActual);
        this.validarNodoFinal();
    }

    public void validarNodoFinal() { //IDENTIFICAR CÓMO SABER EL NOMBRE DE LOS NODOS POR EL PEDO QUE TIENE LA COSA DE QUE NO SÉ CUÁL NODO ES CUÁL
        System.out.println("ENTRA EN FINAL");
        System.out.println(this.nodoActual.nombre);
        if (this.nodoActual != null || !this.nodoActual.nombre.equals("nodoRaiz")) {
            System.out.println("NO ES NULL NODO ACTUAL");
            System.out.println(this.nodoActual);
            if (!this.nodoActual.equals(nodoRaiz) || this.diferenciaDeParentesis != 0) {
                System.out.println("ENTRA YA EN ERROR FINAL");
                this.validarErrorSintactico(this.estructuraDatos.getArregloErroresSinSintacticos()[this.estructuraDatos.getArregloErroresSinSintacticos().length - 1]);
            }
        }
    }

    public int procesarErrorSintactico(ModeloComponenteLexico componenteLexico, int iterador) {
        System.out.println("ENTRAAAAAAAAAAAAAAA");
        this.validarErrorSintactico(componenteLexico);
        return this.encontrarPuntoYcoma(iterador);
    }

    public int encontrarPuntoYcoma(int iterador) {
        while (iterador < this.estructuraDatos.getArregloErroresSinSintacticos().length) {
            System.out.println("ENCONTRANDO ;: " + this.estructuraDatos.getArregloErroresSinSintacticos()[iterador].getLexema());
            if (this.estructuraDatos.getArregloErroresSinSintacticos()[iterador].getLexema().equals(";")) {
                this.nodoActual = this.nodoRaiz;
                System.out.println("YA ESTÁ EN NODO RAIZ");
                break;
            } else {
                iterador++;
            }
        }
        return iterador;
    }

    public void validarErrorSintactico(ModeloComponenteLexico componenteLexico) {
        System.out.println("ENTRA EN MÉTODO DE AGREGAR");
        System.out.println(componenteLexico.getLexema());
        if (!this.estructuraDatos.isRepetido(this.estructuraDatos.getArregloErrores(), componenteLexico)) {
            System.out.println("AGREGA ERROR SIN");
            this.estructuraDatos.agregarComponenteLexico(new ModeloError("ERRSIN",
                    componenteLexico, ExpresionesRegulares.getExpresionesRegulares().length + 1, "Error sintáctico"));
        }
    }

    public void contabilizarCantidadParentesis(int iterador) {

        if (this.nodoActual.equals(nodoSeparadorApertura)) {
            this.diferenciaDeParentesis++;
        } else {
            this.diferenciaDeParentesis--;
        }
    }

    public boolean isSeparadorCierreValido() {

        if (this.nodoActual.equals(nodoSeparadorCierre)) {
            if (this.diferenciaDeParentesis >= 1) {
                return true;
            } else {
                System.out.println("No se permite separador de cierre");
                this.validarErrorSintactico(ultimoSeparador);
                return false;
            }
        } else {
            return true;
        }

    }
}
