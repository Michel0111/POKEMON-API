package org.infantaelena.excepciones;

/**
 * Clase para lanzar extecion si el pokemon no existe
 * @author Michel Garcia, Diego Scoott, Angel Taladriz
 * @version 1.0
 */
public class PokemonNotFoundException extends Exception {
    /**
     * Método para generar un texto cuando se da este tipo de error
     * @param message
     */
    public PokemonNotFoundException(String message) {
        super(message);
    }
}


