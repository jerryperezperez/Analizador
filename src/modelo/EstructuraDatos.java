package modelo;

import java.util.ArrayList;
import modelo.ModeloComponenteLexico;

public class EstructuraDatos {

    private final ArrayList<ModeloError> errores;
    private final ArrayList<ModeloSimbolo> simbolos;
    public ArrayList<ModeloComponenteGenerico> arregloComponenteGenerico;
    private final ArrayList<ModeloComponenteLexico> arregloComponenteLexicoProcesado;
    private final ArrayList<ModeloComponenteLexico> arregloComponenteLexicoRepetido;
    private final ArrayList<ModeloSimbolo> arregloSimbolosRepetidos;
    private ArrayList<ModeloComponenteLexico> arregloErroresSinSintacticos;
    private StringBuilder textoTokens;

    public EstructuraDatos() {
        this.errores = new ArrayList<>();
        this.simbolos = new ArrayList<>();
        this.arregloComponenteLexicoProcesado = new ArrayList<>();
        this.arregloComponenteLexicoRepetido = new ArrayList<>();
        this.arregloErroresSinSintacticos = new ArrayList<>();
        this.arregloSimbolosRepetidos = new ArrayList<>();
        this.textoTokens = new StringBuilder();
        
        this.definirArregloErroresSinSIntacticos();

    }

    public void agregarArregloComponenteGenerico(ModeloComponenteGenerico componenteGenerico) {
        this.arregloComponenteGenerico.add(componenteGenerico);
    }

    public void agregarArregloComponenteLexicoRepetido(ModeloComponenteLexico componenteLexico) {
        this.arregloComponenteLexicoRepetido.add(componenteLexico);
    }

    public boolean isRepetido(ModeloComponenteLexico[] arreglo, ModeloComponenteGenerico componenteGenerico) {
        for (ModeloComponenteLexico elemento : arreglo) {
            if (elemento.comparar(componenteGenerico)) {
                this.arregloComponenteLexicoRepetido.add(new ModeloSimbolo(elemento, componenteGenerico.getNumeroLinea()));
                this.arregloSimbolosRepetidos.add((ModeloSimbolo) elemento);
                return true;
            }
        }
//        if(){
//            
//        }
        return false;
    }
    
    public void agregarComponenteLexico(ModeloSimbolo simbolo) {
        this.simbolos.add(simbolo);
        this.arregloComponenteLexicoProcesado.add(simbolo);
        this.arregloComponenteLexicoRepetido.add(simbolo);
        this.arregloSimbolosRepetidos.add(simbolo);
    }

    public void agregarComponenteLexico(ModeloError error) {
        this.errores.add(error);
        this.arregloComponenteLexicoProcesado.add(error);
        this.arregloComponenteLexicoRepetido.add(error);
    }

    public void prepararTextoTokens() {
        int numeroLineaActual=1;
        for (int iterador = 0; iterador < this.arregloComponenteLexicoRepetido.size(); iterador++) {
            if (!this.arregloComponenteLexicoRepetido.get(iterador).getToken().equals("ERRSIN")) {
                if (iterador == 0) {
                    numeroLineaActual = this.arregloComponenteLexicoRepetido.get(iterador).getNumeroLinea();
                    this.textoTokens.append(this.arregloComponenteLexicoRepetido.get(iterador).getTokenCompleto().concat(" "));
                } else if (this.arregloComponenteLexicoRepetido.get(iterador).getNumeroLinea() <= numeroLineaActual) {
                    this.textoTokens.append(this.arregloComponenteLexicoRepetido.get(iterador).getTokenCompleto().concat(" "));
                } else {
                    this.textoTokens.append("\n");
                    numeroLineaActual = this.arregloComponenteLexicoRepetido.get(iterador).getNumeroLinea();
                    this.textoTokens.append(this.arregloComponenteLexicoRepetido.get(iterador).getTokenCompleto().concat(" "));
                }
            }
        }
    }

    public void vaciarDatos() {
        this.errores.clear();
        this.simbolos.clear();
        this.arregloComponenteLexicoProcesado.clear();
        this.arregloComponenteLexicoRepetido.clear();
        this.textoTokens.setLength(0);
    }

    public ModeloComponenteLexico[] getArregloComponenteLexicoProcesado() {
        return this.arregloComponenteLexicoProcesado.toArray(new ModeloComponenteLexico[this.arregloComponenteLexicoProcesado.size()]);
    }

    public ModeloComponenteLexico[] getArregloComponenteLexicoRepetido() {
        return this.arregloComponenteLexicoRepetido.toArray(new ModeloComponenteLexico[this.arregloComponenteLexicoRepetido.size()]);
    }

    public ModeloSimbolo[] getArregloSimbolos() {
        return this.simbolos.toArray(new ModeloSimbolo[this.simbolos.size()]);
    }
    
public ModeloSimbolo[] getArregloSimbolosRepetidos(){
    return this.arregloSimbolosRepetidos.toArray(new ModeloSimbolo[this.arregloSimbolosRepetidos.size()]);
}


    public ModeloError[] getArregloErrores() {
        
        return this.errores.toArray(new ModeloError[this.errores.size()]);
    }
    
    public void definirArregloErroresSinSIntacticos(){
          for(ModeloComponenteLexico elemento : this.arregloComponenteLexicoRepetido){
            if(!elemento.getToken().equals("ERRSIN")){
                this.arregloErroresSinSintacticos.add(elemento);
            }                
        }
    }
    
    public ModeloComponenteLexico[] getArregloErroresSinSintacticos(){       
      
        return this.arregloErroresSinSintacticos.toArray(new ModeloComponenteLexico[this.arregloErroresSinSintacticos.size()]);
    }

    public String getTextoTokens() {
        return this.textoTokens.toString();
    }
    
}
