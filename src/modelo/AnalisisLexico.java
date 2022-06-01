package modelo;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisisLexico {
    private String[] lineaConEspaciosVacios;
    private final EstructuraDatos estructuraDatos;
    private Pattern patron;
    private Matcher comparador;

    public AnalisisLexico(EstructuraDatos estructuraDatos) {
        this.estructuraDatos = estructuraDatos;     
    }

    public void separarLineas(String texto) {
        this.lineaConEspaciosVacios = texto.split("\n");
        for (int i = 0; i < this.lineaConEspaciosVacios.length; i++) {
            this.lineaConEspaciosVacios[i] = this.lineaConEspaciosVacios[i].replaceAll("( )+", " ");
            if (lineaConEspaciosVacios[i].startsWith(" ")) {
                lineaConEspaciosVacios[i] = lineaConEspaciosVacios[i].replaceFirst("( )+", "");
            }
            this.separarLexemas(lineaConEspaciosVacios[i], i);
        }
    }

    private void separarLexemas(String linea, int numeroLinea) {
        if (!linea.isEmpty()) {
            for (String lexema : linea.split("( )+")) {
                this.identificarComponenteLexico(new ModeloComponenteGenerico(lexema, numeroLinea + 1));
            }
        }
    }

    private void identificarComponenteLexico(ModeloComponenteGenerico componenteGenerico) {
        if (!this.estructuraDatos.isRepetido(this.estructuraDatos.getArregloComponenteLexicoProcesado(), componenteGenerico)) {
            for (int iteradorExpresion = 0; iteradorExpresion < ExpresionesRegulares.getExpresionesRegulares().length; iteradorExpresion++) {
                if (this.detectarLexema(componenteGenerico.getLexema(), ExpresionesRegulares.getExpresionesRegulares()[iteradorExpresion])) {
                    this.estructuraDatos.agregarComponenteLexico(new ModeloSimbolo(ExpresionesRegulares.getPrefijosTokens()[iteradorExpresion], componenteGenerico, iteradorExpresion));
                    break;
                } else if ((iteradorExpresion == ExpresionesRegulares.getExpresionesRegulares().length - 1)) {
                    this.estructuraDatos.agregarComponenteLexico(new ModeloError(ExpresionesRegulares.getPrefijoError()[0], componenteGenerico, iteradorExpresion, "Error léxico"));
                }
            }
        }
    }

    private boolean detectarLexema(String lexema, String expresionRegular) {
        this.patron = Pattern.compile(expresionRegular);
        this.comparador = this.patron.matcher(lexema);
        return this.comparador.matches();
    }
}
