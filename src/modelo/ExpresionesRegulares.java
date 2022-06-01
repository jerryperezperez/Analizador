/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public final class ExpresionesRegulares {
    
    private static final String[] expresionesRegulares = {"\\{|\\(|\\)|\\}|,|;", "\\+|\\*|/|%|-", "-([A-Z]|[a-z])+[0-9]*-", "=", "(\\|\\||&&)",
        "(-7[0-9]*\\.[0]*[1-9]+|7[0-9]*\\.[0]*[1-9]+)", "(-7[0-9]*|7[0-9]*)", "simpleV|numPointV|textV|numCerradoV", "'.'", "\".*\""};

    public static final String[] prefijosTokens = {"SEP", "ARI", "IDE", "ASI", "LOG", "DEC", "ENT", "TDA", "CHR", "STR"};

    public static final String[] prefijoError = {"ERR", "ERRSIN", "ERRSEM"};
    
    public static final String expresionSeparadorApertura = "(";
    
    public static final String expresionSeparadorCierre = ")";
    
    public static final String expresionOperadores = "ARI";
    
    public static final String expresionPuntoYcoma = ";";
    
    public static final String expresionTDA = "TDA";
    
    public static final String expresionIDE = "IDE";
    
    public static final String expresionAsignacion = "=";
    
    public static final String expresionComa = ",";
    
    public static final String expresionDatos[]= {"IDE", "DEC", "ENT", "CHR", "STR"};

    public static String[] getPrefijosTokens() {
        return prefijosTokens;
    }

    public static String[] getExpresionesRegulares() {
        return expresionesRegulares;
    }

    public static String[] getPrefijoError() {
        return prefijoError;
    }
}
